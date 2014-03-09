package com.nuvola.gxpenses.client.web.application.transaction;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.common.shared.business.Transaction;

public interface TransactionUiHandlers extends UiHandlers {
    void loadTransactions(Integer start, Integer length);

    void addNewTransaction(Widget relativeTo);

    void removeTransaction(Transaction transaction);
}
