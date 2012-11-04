package com.nuvola.gxpenses.server.rest;

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
@Path("/authentication")
public class AuthenticationResource {

    @Autowired
    @Qualifier("authenticationProvider")
    AuthenticationManager authenticationManager;

    @POST
    public Boolean authenticate(UserCredentials credentials) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(credentials.getUsername(),
                credentials.getPassword());
        try {
            Authentication authenticated = authenticationManager.authenticate(authentication);
            return authenticated.isAuthenticated();
        } catch (Exception e) {
            return false;
        }
    }

}
