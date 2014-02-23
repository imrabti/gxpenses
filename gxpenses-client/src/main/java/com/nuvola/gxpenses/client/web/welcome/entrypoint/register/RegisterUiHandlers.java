package com.nuvola.gxpenses.client.web.welcome.entrypoint.register;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.UserProxy;

public interface RegisterUiHandlers extends UiHandlers {
    void register(UserProxy user);
}
