package com.nuvola.gxpenses.server.util;

import com.nuvola.gxpenses.shared.domaine.User;

public interface SecurityContext {
    User getCurrentUser();
}
