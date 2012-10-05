package com.nuvola.gxpenses.client.gin;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.nuvola.gxpenses.client.BootStrapper;

public class PageSizeProvider implements Provider<Integer> {

    private final BootStrapper bootStrapper;

    @Inject
    public PageSizeProvider(final BootStrapper bootStrapper) {
        this.bootStrapper = bootStrapper;
    }

    @Override
    public Integer get() {
        return bootStrapper.getCurrentUser().getPageSize().getValue();
    }

}
