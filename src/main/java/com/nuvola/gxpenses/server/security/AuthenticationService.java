package com.nuvola.gxpenses.server.security;

import com.nuvola.gxpenses.server.business.User;

public interface AuthenticationService {
    User currentUser();

    Boolean authenticate(String username, String password);
}
