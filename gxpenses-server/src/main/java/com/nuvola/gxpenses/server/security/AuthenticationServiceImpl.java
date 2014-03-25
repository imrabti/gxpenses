package com.nuvola.gxpenses.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nuvola.gxpenses.common.shared.business.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    @Qualifier("authenticationProvider")
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityContextProvider securityContext;

    @Override
    @Secured({ "ROLE_USER" })
    public User currentUser() {
        return securityContext.getCurrentUser();
    }

    @Override
    public Boolean authenticate(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authenticated = authenticationManager.authenticate(authentication);
            return authenticated.isAuthenticated();
        } catch (Exception e) {
            return false;
        }
    }
}
