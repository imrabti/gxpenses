package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.text.shared.AbstractRenderer;
import com.nuvola.gxpenses.server.business.Account;

public class AccountRenderer extends AbstractRenderer<Account> {

    @Override
    public String render(Account object) {
        if (object != null) {
            return object.getName();
        } else {
            return "";
        }
    }

}
