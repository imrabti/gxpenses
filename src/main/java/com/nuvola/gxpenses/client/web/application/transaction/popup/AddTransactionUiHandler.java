package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.shared.domaine.Transaction;

public interface AddTransactionUiHandler extends UiHandlers {
    void saveTransaction(Transaction transaction);

    void close();
}
