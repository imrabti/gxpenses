package com.nuvola.gxpenses.server.security;

public interface AuthenticationService {
    Boolean authenticate(String username, String password);
}
