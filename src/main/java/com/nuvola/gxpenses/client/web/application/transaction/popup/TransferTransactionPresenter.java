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
import com.nuvola.gxpenses.client.request.proxy.TransferTransactionProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;

public class TransferTransactionPresenter extends PresenterWidget<TransferTransactionPresenter.MyView>
        implements TransferTransactionUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<TransferTransactionUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(TransferTransactionProxy transferTransaction);
    }

    private final GxpensesRequestFactory requestFactory;
    private final MessageBundle messageBundle;

    private Widget relativeTo;
    private TransactionRequest currentContext;

    @Inject
    public TransferTransactionPresenter(final EventBus eventBus, final MyView view,
                                        final GxpensesRequestFactory requestFactory,
                                        final MessageBundle messageBundle) {
        super(eventBus, view);
        this.requestFactory = requestFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveTransfer(TransferTransactionProxy transferTransaction) {
        currentContext.createNewTransferTransaction(transferTransaction).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
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
        currentContext = requestFactory.transactionService();
        TransferTransactionProxy newTransfer = currentContext.create(TransferTransactionProxy.class);
        getView().edit(newTransfer);
        getView().showRelativeTo(relativeTo);
    }
}
