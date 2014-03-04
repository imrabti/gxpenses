package com.nuvola.gxpenses.client.rest;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;
import com.nuvola.gxpenses.common.shared.business.User;
import com.nuvola.gxpenses.common.shared.rest.ResourcesPath;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path(ResourcesPath.USER)
public interface UserService extends RestService {
    @POST
    RestAction<Void> createUser(User user);

    @PUT
    RestAction<Void> updateUser(User user);

    @Path(ResourcesPath.PASSWORD)
    PasswordService password();

    @Path(ResourcesPath.TAG)
    TagService tag();

    @Path(ResourcesPath.PAYEE)
    PayeeService payee();
}
