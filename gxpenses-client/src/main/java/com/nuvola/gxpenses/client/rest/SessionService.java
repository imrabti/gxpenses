package com.nuvola.gxpenses.client.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;
import com.nuvola.gxpenses.common.shared.business.User;
import com.nuvola.gxpenses.common.shared.dto.UserCredentials;
import com.nuvola.gxpenses.common.shared.rest.ResourcesPath;

@Path(ResourcesPath.SESSION)
public interface SessionService extends RestService {
    @GET
    RestAction<User> getSession();

    @POST
    RestAction<Boolean> createNewSession(UserCredentials userCredentials);
}
