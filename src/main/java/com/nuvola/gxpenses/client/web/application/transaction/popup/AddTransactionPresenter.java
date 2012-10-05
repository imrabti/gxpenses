package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.rest.SuggestionListFactory;
import com.nuvola.gxpenses.client.rest.TransactionService;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.type.TransactionType;
import org.fusesource.restygwt.client.Method;

public class AddTransactionPresenter extends PresenterWidget<AddTransactionPresenter.MyView>
        implements AddTransactionUiHandler {

    public interface MyView extends PopupView, HasUiHandlers<AddTransactionUiHandler> {
        void showRelativeTo(Widget widget);

        void edit(Transaction transfer);
    }

    private final TransactionService transactionService;
    private final SuggestionListFactory suggestionListFactory;
    private final MessageBundle messageBundle;

    private Widget relativeTo;
    private Account selectedAccount;

    @Inject
    public AddTransactionPresenter(final EventBus eventBus, final MyView view,
                                   final TransactionService transactionService,
                                   final SuggestionListFactory suggestionListFactory,
                                   final MessageBundle messageBundle) {
        super(eventBus, view);
        this.transactionService = transactionService;
        this.suggestionListFactory = suggestionListFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveTransaction(final Transaction transaction) {
        transactionService.createTransaction(transaction, new MethodCallbackImpl<Void>() {
            @Override
            public void onSuccess(Method method, Void aVoid) {
                suggestionListFactory.updatePayeeList(transaction.getPayee());
                suggestionListFactory.updateTagsList(transaction.getTags());

                GlobalMessageEvent.fire(this, messageBundle.transactionAdded());
                AccountBalanceChangedEvent.fire(this);
            }
        });
    }

    @Override
    public void close() {
        PopupClosedEvent.fire(this);
    }

    public void setRelativeTo(Widget relativeTo) {
        this.relativeTo = relativeTo;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        Transaction newTransaction = new Transaction();
        newTransaction.setType(TransactionType.EXPENSE);
        newTransaction.setAccount(selectedAccount);
        getView().edit(newTransaction);
        getView().showRelativeTo(relativeTo);
    }

}
