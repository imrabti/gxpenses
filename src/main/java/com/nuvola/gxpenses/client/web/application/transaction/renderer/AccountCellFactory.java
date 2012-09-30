package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.cell.client.ActionCell;
import com.nuvola.gxpenses.shared.domaine.Account;

public interface AccountCellFactory {
    AccountCell create(ActionCell.Delegate<Account> delegate);
}
