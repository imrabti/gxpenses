package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.cell.client.ActionCell;
import com.nuvola.gxpenses.client.request.proxy.TransactionProxy;

public interface TransactionCellFactory {
    TransactionCell create(ActionCell.Delegate<TransactionProxy> delegate);
}
