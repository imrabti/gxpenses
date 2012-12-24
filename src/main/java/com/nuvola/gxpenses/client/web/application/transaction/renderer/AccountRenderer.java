package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.text.shared.AbstractRenderer;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;

public class AccountRenderer extends AbstractRenderer<AccountProxy> {
    @Override
    public String render(AccountProxy object) {
        if (object != null) {
            return object.getName();
        } else {
            return "";
        }
    }
}
