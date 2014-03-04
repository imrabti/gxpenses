package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.TransactionProxy;

public interface AddTransactionUiHandler extends UiHandlers {
    void saveTransaction(final TransactionProxy transaction);

    void close();
}
