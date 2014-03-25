/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.client.util;

import java.util.List;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.nuvola.gxpenses.client.rest.AccountService;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.Account;

public class ValueListFactory {
    private final RestDispatchAsync dispatcher;
    private final AccountService accountService;

    private List<Account> listAccounts;

    @Inject
    public ValueListFactory(RestDispatchAsync dispatcher,
                            AccountService accountService) {
        this.dispatcher = dispatcher;
        this.accountService = accountService;
    }

    public List<Account> getListAccounts() {
        if (listAccounts == null) {
            updateListAccount();
        }

        return listAccounts;
    }

    public void updateListAccount() {
        dispatcher.execute(accountService.findAllAccounts(), new AsyncCallbackImpl<List<Account>>() {
            @Override
            public void onReceive(List<Account> response) {
                listAccounts = response;
            }
        });
    }
}
