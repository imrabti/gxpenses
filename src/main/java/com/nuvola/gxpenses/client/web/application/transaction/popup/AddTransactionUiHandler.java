package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.server.business.Transaction;

public interface AddTransactionUiHandler extends UiHandlers {
    void saveTransaction(final Transaction transaction);

    void close();
}
