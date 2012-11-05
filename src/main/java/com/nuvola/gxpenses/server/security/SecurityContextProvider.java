package com.nuvola.gxpenses.server.security;

import com.nuvola.gxpenses.shared.domaine.User;

public interface SecurityContextProvider {
    User getCurrentUser();
}
