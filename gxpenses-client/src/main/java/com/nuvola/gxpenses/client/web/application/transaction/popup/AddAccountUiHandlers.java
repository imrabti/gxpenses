package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;

public interface AddAccountUiHandlers extends UiHandlers {
    void saveAccount(AccountProxy account);
}
