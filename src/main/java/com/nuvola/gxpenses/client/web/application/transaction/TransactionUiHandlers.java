package com.nuvola.gxpenses.client.web.application.transaction;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.TransactionProxy;

public interface TransactionUiHandlers extends UiHandlers {
    void loadTransactions(Integer start, Integer length);

    void addNewTransaction(Widget relativeTo);

    void removeTransaction(TransactionProxy transaction);
}
