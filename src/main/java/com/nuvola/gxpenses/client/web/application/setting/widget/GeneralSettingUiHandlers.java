package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.UserProxy;

public interface GeneralSettingUiHandlers extends UiHandlers {
    void saveSetting(UserProxy editedUser);
}
