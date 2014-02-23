package com.nuvola.gxpenses.client.security;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

public class LoggedInGatekeeper implements Gatekeeper {
    private final SecurityUtils securityUtils;

    @Inject
    public LoggedInGatekeeper(final SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Override
    public boolean canReveal() {
        return securityUtils.isLoggedIn();
    }
}
