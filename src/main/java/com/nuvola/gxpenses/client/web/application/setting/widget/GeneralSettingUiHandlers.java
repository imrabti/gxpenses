package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.server.business.User;

public interface GeneralSettingUiHandlers extends UiHandlers {
    void saveSetting(User editedUser);
}
