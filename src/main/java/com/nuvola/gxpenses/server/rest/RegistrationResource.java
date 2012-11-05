package com.nuvola.gxpenses.server.rest;

import com.nuvola.gxpenses.server.service.UserService;
import com.nuvola.gxpenses.shared.domaine.User;
import com.nuvola.gxpenses.shared.dto.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Component
@Path("/registration")
public class RegistrationResource {

    @Autowired
    UserService userService;

    @POST
    public void register(User user) {
        userService.createUser(user);
    }

}
