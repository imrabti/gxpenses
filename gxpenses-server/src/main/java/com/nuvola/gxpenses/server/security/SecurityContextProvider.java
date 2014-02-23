package com.nuvola.gxpenses.server.security;

import com.nuvola.gxpenses.server.business.User;

public interface SecurityContextProvider {
    User getCurrentUser();
}
