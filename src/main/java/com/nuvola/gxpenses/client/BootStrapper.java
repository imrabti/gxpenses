package com.nuvola.gxpenses.client;

import com.nuvola.gxpenses.server.business.User;

public interface BootStrapper {

    void init();

    void logout();

    User getCurrentUser();

}
