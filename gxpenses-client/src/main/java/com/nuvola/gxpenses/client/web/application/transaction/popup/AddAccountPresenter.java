package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.shared.type.AccountType;

public class AddAccountPresenter extends PresenterWidget<AddAccountPresenter.MyView> implements AddAccountUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<AddAccountUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(AccountProxy account);
    }

    private final GxpensesRequestFactory requestFactory;
    private final MessageBundle messageBundle;
    private final ValueListFactory valueListFactory;

    private AccountRequest currentContext;
    private Widget relativeTo;

    @Inject
    public AddAccountPresenter(final EventBus eventBus, final MyView view,
                               final GxpensesRequestFactory requestFactory,
                               final MessageBundle messageBundle,
                               final ValueListFactory valueListFactory) {
        super(eventBus, view);

        this.requestFactory = requestFactory;
        this.messageBundle = messageBundle;
        this.valueListFactory = valueListFactory;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveAccount(AccountProxy account) {
        currentContext.createAccount(account).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
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
        currentContext = requestFactory.accountService();
        AccountProxy newAccount = currentContext.create(AccountProxy.class);
        newAccount.setType(AccountType.CASH);
        getView().edit(newAccount);
        getView().showRelativeTo(relativeTo);
    }
}
