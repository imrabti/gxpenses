package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.PasswordProxy;

public interface PasswordSettingUiHandlers extends UiHandlers {
    void savePassword(PasswordProxy password);
}
