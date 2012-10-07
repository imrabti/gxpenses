package com.nuvola.gxpenses.server.rest;

import com.nuvola.gxpenses.server.service.UserService;
import com.nuvola.gxpenses.server.util.SecurityContext;
import com.nuvola.gxpenses.shared.domaine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/user")
public class UserResource extends Resource {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityContext securityContext;

    @GET
    public User getLoggedInUser() {
        return securityContext.getCurrentUser();
    }

    @GET
    @Path("/tag")
    public List<String> getTags() {
        User currentUser = securityContext.getCurrentUser();
        return userService.findAllTagsForUser(currentUser.getId());
    }

    @GET
    @Path("/payee")
    public List<String> getPayees() {
        User currentUser = securityContext.getCurrentUser();
        return userService.findAllPayeeForUser(currentUser.getId());
    }

    @POST
    @Path("/tag")
    public Response createTags(List<String> tags) {
        User currentUser = securityContext.getCurrentUser();
        userService.createTags(currentUser.getId(), tags);
        return Response.ok().build();
    }

}
