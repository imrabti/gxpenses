package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.TransactionRequest;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;
import com.nuvola.gxpenses.client.request.proxy.TransactionProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.shared.type.TransactionType;

public class AddTransactionPresenter extends PresenterWidget<AddTransactionPresenter.MyView>
        implements AddTransactionUiHandler {
    public interface MyView extends PopupView, HasUiHandlers<AddTransactionUiHandler> {
        void showRelativeTo(Widget widget);

        void edit(TransactionProxy transfer);
    }

    private final GxpensesRequestFactory requestFactory;
    private final SuggestionListFactory suggestionListFactory;
    private final MessageBundle messageBundle;

    private Widget relativeTo;
    private TransactionRequest currentContext;
    private AccountProxy selectedAccount;

    @Inject
    public AddTransactionPresenter(final EventBus eventBus, final MyView view,
                                   final GxpensesRequestFactory requestFactory,
                                   final SuggestionListFactory suggestionListFactory,
                                   final MessageBundle messageBundle) {
        super(eventBus, view);
        this.requestFactory = requestFactory;
        this.suggestionListFactory = suggestionListFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveTransaction(final TransactionProxy transaction) {
        currentContext.createNewTransaction(transaction).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
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

    public void setSelectedAccount(AccountProxy selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        currentContext = requestFactory.transactionService();
        TransactionProxy newTransaction = currentContext.create(TransactionProxy.class);
        newTransaction.setType(TransactionType.EXPENSE);
        newTransaction.setAccount(selectedAccount);
        getView().edit(newTransaction);
        getView().showRelativeTo(relativeTo);
    }
}
