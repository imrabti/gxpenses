package com.nuvola.gxpenses.client.util;

import com.google.inject.Inject;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;

import java.util.List;

public class ValueListFactory {
    private final GxpensesRequestFactory requestFactory;

    private List<AccountProxy> listAccounts;

    @Inject
    public ValueListFactory(final GxpensesRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public List<AccountProxy> getListAccounts() {
        if (listAccounts == null) {
            updateListAccount();
        }

        return listAccounts;
    }

    public void updateListAccount() {
        requestFactory.accountService().findAllAccountsByUserId().fire(new ReceiverImpl<List<AccountProxy>>() {
            @Override
            public void onSuccess(List<AccountProxy> accounts) {
                listAccounts = accounts;
            }
        });
    }
}
