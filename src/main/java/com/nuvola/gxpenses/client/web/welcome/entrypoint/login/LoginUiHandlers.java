package com.nuvola.gxpenses.client.web.welcome.entrypoint.login;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LoginUiHandlers extends UiHandlers {
    void login(String email, String password);
}
