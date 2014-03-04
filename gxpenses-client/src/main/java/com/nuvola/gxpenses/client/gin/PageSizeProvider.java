package com.nuvola.gxpenses.client.gin;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class PageSizeProvider implements Provider<Integer> {
    private final CurrentUser currentUser;

    @Inject
    PageSizeProvider(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public Integer get() {
        return currentUser.getUser().getPageSize().getValue();
    }
}
