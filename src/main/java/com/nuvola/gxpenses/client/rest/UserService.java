package com.nuvola.gxpenses.client.rest;

import com.nuvola.gxpenses.shared.domaine.User;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

public interface UserService extends RestService {
    @GET
    @Path("/user")
    void getLoggedInUser(MethodCallback<User> callback);

    @GET
    @Path("/user/tags")
    void getTags(MethodCallback<List<String>> callback);

    @GET
    @Path("/user/payees")
    void getPayees(MethodCallback<List<String>> callback);

    @PUT
    @Path("/user/tags")
    void updateTags(List<String> tags, MethodCallBackImpl<Void> callBack);
}
