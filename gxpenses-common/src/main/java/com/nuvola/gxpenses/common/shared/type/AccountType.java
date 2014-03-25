/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.common.shared.type;

public enum AccountType {

    CASH("Cash", "CASH"),
    SAVING("Saving", "SAV"),
    CHECKING("Checking", "CHEC");

    private final String label;
    private final String value;

    private AccountType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return label;
    }

}
