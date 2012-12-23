package com.nuvola.gxpenses.client.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nuvola.gxpenses.client.request.proxy.UserProxy;
import com.nuvola.gxpenses.server.security.AuthenticationServiceImpl;
import com.nuvola.gxpenses.server.util.SpringServiceLocator;

@Service(value = AuthenticationServiceImpl.class, locator = SpringServiceLocator.class)
public interface AuthenticationRequest extends RequestContext {
    Request<UserProxy> currentUser();

    Request<Boolean> authenticate(String username, String password);
}
