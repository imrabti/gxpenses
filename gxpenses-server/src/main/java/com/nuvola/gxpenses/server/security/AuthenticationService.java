package com.nuvola.gxpenses.server.security;

import com.nuvola.gxpenses.common.shared.business.User;

public interface AuthenticationService {
    User currentUser();

    Boolean authenticate(String username, String password);
}
