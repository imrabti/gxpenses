package com.nuvola.gxpenses.client;

import com.nuvola.gxpenses.client.request.proxy.UserProxy;

public interface BootStrapper {
    void init();

    void logout();

    UserProxy getCurrentUser();
}
