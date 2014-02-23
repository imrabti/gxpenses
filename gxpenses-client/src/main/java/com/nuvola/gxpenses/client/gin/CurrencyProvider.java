package com.nuvola.gxpenses.client.gin;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.nuvola.gxpenses.client.BootStrapper;

public class CurrencyProvider implements Provider<String> {
    private final BootStrapper bootStrapper;

    @Inject
    public CurrencyProvider(final BootStrapper bootStrapper) {
        this.bootStrapper = bootStrapper;
    }

    @Override
    public String get() {
        return bootStrapper.getCurrentUser().getCurrency().getValue();
    }
}
