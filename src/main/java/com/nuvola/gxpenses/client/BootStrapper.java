package com.nuvola.gxpenses.client;

import com.nuvola.gxpenses.shared.domaine.User;

public interface BootStrapper {

    void init();

    void logout();

    User getCurrentUser();

}
