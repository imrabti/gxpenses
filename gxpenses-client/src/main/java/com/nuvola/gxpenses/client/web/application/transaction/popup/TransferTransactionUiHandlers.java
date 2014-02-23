package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.TransferTransactionProxy;

public interface TransferTransactionUiHandlers extends UiHandlers {
    void saveTransfer(TransferTransactionProxy transferTransaction);

    void close();
}
