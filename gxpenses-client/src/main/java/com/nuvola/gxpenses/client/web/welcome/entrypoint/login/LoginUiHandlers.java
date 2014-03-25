package com.nuvola.gxpenses.client.web.welcome.entrypoint.login;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.common.shared.dto.UserCredentials;

public interface LoginUiHandlers extends UiHandlers {
    void login(UserCredentials credentials);
}
