package com.nuvola.gxpenses.client.web.application.transaction.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.nuvola.gxpenses.server.business.Transaction;

public class TransactionRemovedEvent extends GwtEvent<TransactionRemovedEvent.TransactionRemovedHandler> {

    public static Type<TransactionRemovedHandler> TYPE = new Type<TransactionRemovedHandler>();

    public interface TransactionRemovedHandler extends EventHandler {
        void onTransactionRemoved(TransactionRemovedEvent event);
    }

    private Transaction transaction;

    public TransactionRemovedEvent(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public Type<TransactionRemovedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(TransactionRemovedHandler handler) {
        handler.onTransactionRemoved(this);
    }

}
