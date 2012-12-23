package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.server.dto.TransferTransaction;

public interface TransferTransactionUiHandlers extends UiHandlers {
    void saveTransfer(TransferTransaction transferTransaction);

    void close();
}
