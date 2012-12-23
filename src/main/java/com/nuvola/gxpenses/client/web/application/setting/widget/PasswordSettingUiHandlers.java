package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.server.dto.Password;

public interface PasswordSettingUiHandlers extends UiHandlers {
    void savePassword(Password password);
}
