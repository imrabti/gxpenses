/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.client.web.application.transaction.widget;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.NoElementFoundEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.AccountService;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.event.TransactionFiltreChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddAccountPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.popup.TransferTransactionPresenter;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.Account;
import com.nuvola.gxpenses.common.shared.type.PeriodType;
import com.nuvola.gxpenses.common.shared.type.TransactionType;

public class AccountSiderPresenter extends PresenterWidget<AccountSiderPresenter.MyView>
        implements AccountSiderUiHandlers, AccountBalanceChangedEvent.AccountBalanceChangedHandler,
        PopupClosedEvent.PopupClosedHandler {
    public interface MyView extends View, HasUiHandlers<AccountSiderUiHandlers> {
        void setData(List<Account> accounts);

        void showTransferButton(Boolean visible);

        void switchTransferStyle();

        void clearSelection();
    }

    private final RestDispatchAsync dispatcher;
    private final AccountService accountService;
    private final AddAccountPresenter addAccountPresenter;
    private final TransferTransactionPresenter transferTransactionPresenter;
    private final MessageBundle messageBundle;
    private final ValueListFactory valueListFactory;

    private PeriodType currentPeriodType;
    private TransactionType currentTransactionType;

    @Inject
    AccountSiderPresenter(EventBus eventBus,
                          MyView view,
                          MessageBundle messageBundle,
                          RestDispatchAsync dispatcher,
                          AccountService accountService,
                          AddAccountPresenter addAccountPresenter,
                          TransferTransactionPresenter transferTransactionPresenter,
                          ValueListFactory valueListFactory) {
        super(eventBus, view);

        this.messageBundle = messageBundle;
        this.dispatcher = dispatcher;
        this.accountService = accountService;
        this.addAccountPresenter = addAccountPresenter;
        this.transferTransactionPresenter = transferTransactionPresenter;
        this.valueListFactory = valueListFactory;

        currentPeriodType = PeriodType.THIS_MONTH;
        currentTransactionType = TransactionType.ALL;

        getView().setUiHandlers(this);
    }

    @Override
    public void onPopupClosed(PopupClosedEvent event) {
        getView().switchTransferStyle();
    }

    @Override
    public void onAccountBalanceChanged(AccountBalanceChangedEvent event) {
        fireLoadListAccounts();
    }

    @Override
    public void addNewAccount(Widget relativeTo) {
        addAccountPresenter.setRelativeTo(relativeTo);
        addToPopupSlot(addAccountPresenter, false);
    }

    @Override
    public void addNewTransfert(Widget relativeTo) {
        transferTransactionPresenter.setRelativeTo(relativeTo);
        addToPopupSlot(transferTransactionPresenter, false);
    }

    @Override
    public void removeAccount(Account account) {
        Boolean decision = Window.confirm(messageBundle.accountConf());
        if (decision) {
            dispatcher.execute(accountService.removeAccount(account.getId()), new AsyncCallbackImpl<Void>() {
                @Override
                public void onReceive(Void response) {
                    valueListFactory.updateListAccount();
                    AccountChangedEvent.fire(this);
                    GlobalMessageEvent.fire(this, messageBundle.accountRemoved());
                    fireLoadListAccounts();
                }
            });
        }
    }

    @Override
    public void accountSelected(Account account) {
        AccountChangedEvent.fire(this, account, currentPeriodType, currentTransactionType);
    }

    @Override
    public void filterChange(PeriodType newPeriod, TransactionType newType) {
        currentPeriodType = newPeriod;
        currentTransactionType = newType;
        TransactionFiltreChangedEvent.fire(this, newPeriod, newType);
    }

    @Override
    protected void onReveal() {
        fireLoadListAccounts();
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(AccountBalanceChangedEvent.getType(), this);
        addRegisteredHandler(PopupClosedEvent.getType(), this);
    }

    @Override
    protected void onHide() {
        getView().clearSelection();
    }

    private void fireLoadListAccounts() {
        dispatcher.execute(accountService.findAllAccounts(), new AsyncCallbackImpl<List<Account>>() {
            @Override
            public void onReceive(List<Account> response) {
                getView().showTransferButton(response.size() >= 2);
                getView().setData(response);

                NoElementFoundEvent.fire(this, response.size());
            }
        });
    }
}
