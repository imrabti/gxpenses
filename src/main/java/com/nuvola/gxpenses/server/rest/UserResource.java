package com.nuvola.gxpenses.server.rest;

import com.nuvola.gxpenses.server.service.UserService;
import com.nuvola.gxpenses.server.util.SecurityContext;
import com.nuvola.gxpenses.shared.domaine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/user")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityContext securityContext;

    @GET
    public User getLoggedInUser() {
        return securityContext.getCurrentUser();
    }

    @GET
    @Path("/tags")
    public List<String> getTags() {
        User currentUser = securityContext.getCurrentUser();
        return userService.findAllTagsForUser(currentUser.getId());
    }

    @GET
    @Path("/payees")
    public List<String> getPayees() {
        User currentUser = securityContext.getCurrentUser();
        return userService.findAllPayeeForUser(currentUser.getId());
    }

    @PUT
    @Path("/tags")
    public Response updateTags(List<String> tags) {
        User currentUser = securityContext.getCurrentUser();
        userService.updateUserTags(currentUser.getId(), tags);
        return Response.ok().build();
    }

}
