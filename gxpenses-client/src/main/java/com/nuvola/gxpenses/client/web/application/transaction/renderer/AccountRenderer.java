/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.text.shared.AbstractRenderer;
import com.nuvola.gxpenses.common.shared.business.Account;

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
