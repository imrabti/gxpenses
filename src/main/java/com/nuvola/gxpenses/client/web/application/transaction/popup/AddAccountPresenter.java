package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.BootStrapper;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.AccountService;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.server.business.Account;
import com.nuvola.gxpenses.shared.type.AccountType;

public class AddAccountPresenter extends PresenterWidget<AddAccountPresenter.MyView> implements AddAccountUiHandlers {

    public interface MyView extends PopupView, HasUiHandlers<AddAccountUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(Account account);
    }

    private final AccountService accountService;
    private final BootStrapper bootStrapper;
    private final MessageBundle messageBundle;
    private final ValueListFactory valueListFactory;

    private Widget relativeTo;

    @Inject
    public AddAccountPresenter(final EventBus eventBus, final MyView view,
                               final AccountService accountService,
                               final BootStrapper bootStrapper,
                               final MessageBundle messageBundle,
                               final ValueListFactory valueListFactory) {
        super(eventBus, view);

        this.accountService = accountService;
        this.bootStrapper = bootStrapper;
        this.messageBundle = messageBundle;
        this.valueListFactory = valueListFactory;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveAccount(Account account) {
        accountService.createAccount(account, new MethodCallbackImpl<Void>() {
            @Override
            public void handleSuccess(Void aVoid) {
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
        super.onReveal();

        Account newAccount = new Account();
        newAccount.setType(AccountType.CASH);
        newAccount.setUser(bootStrapper.getCurrentUser());
        getView().edit(newAccount);
        getView().showRelativeTo(relativeTo);
    }

}
