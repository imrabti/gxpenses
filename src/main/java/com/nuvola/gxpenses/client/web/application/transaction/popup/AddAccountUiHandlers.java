package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.server.business.Account;

public interface AddAccountUiHandlers extends UiHandlers {
    void saveAccount(Account account);
}
