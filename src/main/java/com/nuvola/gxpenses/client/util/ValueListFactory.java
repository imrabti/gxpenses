package com.nuvola.gxpenses.client.util;

import com.google.inject.Inject;
import com.nuvola.gxpenses.client.rest.AccountService;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.shared.domaine.Account;
import org.fusesource.restygwt.client.Method;

import java.util.List;

public class ValueListFactory {

    private final AccountService accountService;

    private List<Account> listAccounts;

    @Inject
    public ValueListFactory(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<Account> getListAccounts() {
        if (listAccounts == null) {
            updateListAccount();
        }

        return listAccounts;
    }

    public void updateListAccount() {
        accountService.getAccounts(new MethodCallbackImpl<List<Account>>() {
            @Override
            public void onSuccess(Method method, List<Account> accounts) {
                listAccounts = accounts;
            }
        });
    }

}
