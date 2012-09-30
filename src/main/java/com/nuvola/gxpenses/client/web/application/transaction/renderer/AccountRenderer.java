package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.nuvola.gxpenses.shared.domaine.Account;

import com.google.gwt.text.shared.AbstractRenderer;

public class AccountRenderer extends AbstractRenderer<Account> {

	@Override
	public String render(Account object) {
		if(object != null) {
			return object.getName();
        } else {
			return "";
        }
	}
}
