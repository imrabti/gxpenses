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
import com.nuvola.gxpenses.client.rest.TransactionService;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.shared.dto.TransferTransaction;

public class TransferTransactionPresenter extends PresenterWidget<TransferTransactionPresenter.MyView>
        implements TransferTransactionUiHandlers {

    public interface MyView extends PopupView, HasUiHandlers<TransferTransactionUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(TransferTransaction transferTransaction);
    }

    private final TransactionService transactionService;
    private final MessageBundle messageBundle;

    private Widget relativeTo;

    @Inject
    public TransferTransactionPresenter(final EventBus eventBus, final MyView view,
                                        final TransactionService transactionService,
                                        final MessageBundle messageBundle) {
        super(eventBus, view);
        this.transactionService = transactionService;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveTransfer(TransferTransaction transferTransaction) {
        transactionService.createTransfer(transferTransaction, new MethodCallbackImpl() {
            @Override
            public void handleSuccess(Object o) {
                GlobalMessageEvent.fire(this, messageBundle.transfertAdded());
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

    @Override
    protected void onReveal() {
        super.onReveal();

        TransferTransaction newTransfer = new TransferTransaction();
        getView().edit(newTransfer);
        getView().showRelativeTo(relativeTo);
    }

}
