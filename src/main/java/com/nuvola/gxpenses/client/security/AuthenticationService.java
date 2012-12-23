package com.nuvola.gxpenses.client.security;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface AuthenticationService extends RestService {
    @POST
    @Path("/authentication")
    void authenticate(UserCredentials userCredentials, MethodCallback<Boolean> callback);
}
