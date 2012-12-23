package com.nuvola.gxpenses.client.security;

import com.nuvola.gxpenses.server.business.User;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface RegistrationService extends RestService {
    @POST
    @Path("/registration")
    void register(User user, MethodCallback<Void> callback);
}
