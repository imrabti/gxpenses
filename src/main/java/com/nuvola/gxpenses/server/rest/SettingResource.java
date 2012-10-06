package com.nuvola.gxpenses.server.rest;

import com.nuvola.gxpenses.server.service.UserService;
import com.nuvola.gxpenses.server.util.SecurityContext;
import com.nuvola.gxpenses.shared.domaine.User;
import com.nuvola.gxpenses.shared.dto.Password;
import com.nuvola.gxpenses.shared.dto.ValidatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/setting")
public class SettingResource extends Resource {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityContext securityContext;

    @GET
    public User getLoggedInUserSettings() {
        String email = securityContext.getCurrentUser().getEmail();
        return userService.findUserByEmail(email);
    }

    @PUT
    public ValidatedResponse<User> updateUserSettings(User user) {
        // TODO : Add server side validation
        userService.updateUser(user);
        return new ValidatedResponse<User>();
    }

    @PUT
    @Path("/password")
    public ValidatedResponse<Password> updateUserPassword(Password password) {
        // TODO : Add server side validation
        User currentUser = securityContext.getCurrentUser();
        userService.updatePassword(currentUser.getId(), password.getNewPassword());
        return new ValidatedResponse<Password>();
    }

    @PUT
    @Path("/tag")
    public Response updateUserTags(List<String> tags) {
        User currentUser = securityContext.getCurrentUser();
        userService.updateTags(currentUser.getId(), tags);
        return Response.ok().build();
    }

}
