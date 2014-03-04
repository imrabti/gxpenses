package com.nuvola.gxpenses.client.util;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.nuvola.gxpenses.client.rest.AccountService;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.Account;

import java.util.List;

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
