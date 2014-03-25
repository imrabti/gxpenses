/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.AccountService;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.Account;
import com.nuvola.gxpenses.common.shared.type.AccountType;

public class AddAccountPresenter extends PresenterWidget<AddAccountPresenter.MyView> implements AddAccountUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<AddAccountUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(Account account);
    }

    private final RestDispatchAsync dispatcher;
    private final AccountService accountService;
    private final MessageBundle messageBundle;
    private final ValueListFactory valueListFactory;

    private Widget relativeTo;

    @Inject
    AddAccountPresenter(EventBus eventBus,
                        MyView view,
                        RestDispatchAsync dispatcher,
                        AccountService accountService,
                        MessageBundle messageBundle,
                        ValueListFactory valueListFactory) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.accountService = accountService;
        this.messageBundle = messageBundle;
        this.valueListFactory = valueListFactory;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveAccount(Account account) {
        dispatcher.execute(accountService.createAccount(account), new AsyncCallbackImpl<Void>() {
            @Override
            public void onReceive(Void response) {
                valueListFactory.updateListAccount();
                GlobalMessageEvent.fire(this, messageBundle.accountAdded());
                AccountBalanceChangedEvent.fire(this);
            }
        });
    }

    public void setRelativeTo(Widget relativeTo) {
        this.relativeTo = relativeTo;
    }

    @Override
    protected void onReveal() {
        Account newAccount = new Account();
        newAccount.setType(AccountType.CASH);
        getView().edit(newAccount);
        getView().showRelativeTo(relativeTo);
    }
}
