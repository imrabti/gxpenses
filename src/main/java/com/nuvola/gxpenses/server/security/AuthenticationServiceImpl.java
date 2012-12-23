package com.nuvola.gxpenses.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    @Qualifier("authenticationProvider")
    AuthenticationManager authenticationManager;

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
