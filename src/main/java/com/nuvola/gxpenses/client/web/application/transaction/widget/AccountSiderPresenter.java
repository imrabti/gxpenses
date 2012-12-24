package com.nuvola.gxpenses.client.web.application.transaction.widget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.NoElementFoundEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.event.TransactionFiltreChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddAccountPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.popup.TransferTransactionPresenter;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

import java.util.List;

public class AccountSiderPresenter extends PresenterWidget<AccountSiderPresenter.MyView>
        implements AccountSiderUiHandlers, AccountBalanceChangedEvent.AccountBalanceChangedHandler,
        PopupClosedEvent.PopupClosedHandler {
    public interface MyView extends View, HasUiHandlers<AccountSiderUiHandlers> {
        void setData(List<AccountProxy> accounts);

        void showTransferButton(Boolean visible);

        void switchTransferStyle();

        void clearSelection();
    }

    private final GxpensesRequestFactory requestFactory;
    private final AddAccountPresenter addAccountPresenter;
    private final TransferTransactionPresenter transferTransactionPresenter;
    private final MessageBundle messageBundle;
    private final ValueListFactory valueListFactory;

    private PeriodType currentPeriodType;
    private TransactionType currentTransactionType;

    @Inject
    public AccountSiderPresenter(EventBus eventBus, MyView view,
                                 final MessageBundle messageBundle,
                                 final GxpensesRequestFactory requestFactory,
                                 final AddAccountPresenter addAccountPresenter,
                                 final TransferTransactionPresenter transferTransactionPresenter,
                                 final ValueListFactory valueListFactory) {
        super(eventBus, view);

        this.messageBundle = messageBundle;
        this.requestFactory = requestFactory;
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
    public void removeAccount(AccountProxy account) {
        Boolean decision = Window.confirm(messageBundle.accountConf());
        if (decision) {
            requestFactory.accountService().removeAccount(account.getId()).fire(new ReceiverImpl<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    valueListFactory.updateListAccount();
                    AccountChangedEvent.fire(this);
                    GlobalMessageEvent.fire(this, messageBundle.accountRemoved());
                    fireLoadListAccounts();
                }
            });
        }
    }

    @Override
    public void accountSelected(AccountProxy account) {
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
        super.onReveal();

        fireLoadListAccounts();
    }

    @Override
    protected void onBind() {
        super.onBind();

        addRegisteredHandler(AccountBalanceChangedEvent.getType(), this);
        addRegisteredHandler(PopupClosedEvent.getType(), this);
    }

    @Override
    protected void onHide() {
        super.onHide();

        getView().clearSelection();
    }

    private void fireLoadListAccounts() {
        requestFactory.accountService().findAllAccountsByUserId().fire(new ReceiverImpl<List<AccountProxy>>() {
            @Override
            public void onSuccess(List<AccountProxy> accounts) {
                getView().showTransferButton(accounts.size() >= 2);
                getView().setData(accounts);

                NoElementFoundEvent.fire(this, accounts.size());
            }
        });
    }
}
