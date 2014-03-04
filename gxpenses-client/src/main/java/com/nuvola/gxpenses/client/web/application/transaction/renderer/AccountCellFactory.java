package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.cell.client.ActionCell;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;

public interface AccountCellFactory {
    AccountCell create(ActionCell.Delegate<AccountProxy> delegate);
}
