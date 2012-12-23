package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.cell.client.ActionCell;
import com.nuvola.gxpenses.server.business.Transaction;

public interface TransactionCellFactory {
    TransactionCell create(ActionCell.Delegate<Transaction> delegate);
}
