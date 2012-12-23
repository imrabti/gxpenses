package com.nuvola.gxpenses.client.security;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

import static com.google.gwt.user.client.rpc.RpcRequestBuilder.STRONG_NAME_HEADER;

public class SecureRequestTransport extends DefaultRequestTransport {
    private final SecurityUtils securityUtils;

    public SecureRequestTransport(final SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Override
    protected void configureRequestBuilder(RequestBuilder builder) {
        builder.setHeader("Content-Type", RequestFactory.JSON_CONTENT_TYPE_UTF8);
        builder.setHeader("pageurl", Window.Location.getHref());
        builder.setHeader(STRONG_NAME_HEADER, GWT.getPermutationStrongName());
        builder.setUser(securityUtils.getUsername());
        builder.setPassword(securityUtils.getPassword());
    }
}
