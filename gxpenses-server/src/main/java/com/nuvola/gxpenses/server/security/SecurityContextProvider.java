package com.nuvola.gxpenses.server.security;

import com.nuvola.gxpenses.common.shared.business.User;

public interface SecurityContextProvider {
    User getCurrentUser();
}
