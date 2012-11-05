package com.nuvola.gxpenses.client.security;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.inject.Inject;
import org.fusesource.restygwt.client.Dispatcher;
import org.fusesource.restygwt.client.Method;

public class SecureDispatcher implements Dispatcher {

    private final SecurityUtils securityUtils;

    @Inject
    public SecureDispatcher(final SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Override
    public Request send(Method method, RequestBuilder requestBuilder) throws RequestException {
        method.user(securityUtils.getUsername());
        method.password(securityUtils.getPassword());

        return requestBuilder.send();
    }

}
