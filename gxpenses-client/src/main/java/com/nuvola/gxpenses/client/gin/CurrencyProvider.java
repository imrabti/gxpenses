package com.nuvola.gxpenses.client.gin;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class CurrencyProvider implements Provider<String> {
    private final CurrentUser currentUser;

    @Inject
    CurrencyProvider(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String get() {
        if (currentUser.getUser() != null) {
            return currentUser.getUser().getCurrency().getValue();
        }
        return "";
    }
}
