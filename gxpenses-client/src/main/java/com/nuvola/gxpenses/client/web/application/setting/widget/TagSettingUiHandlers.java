package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.gwtplatform.mvp.client.UiHandlers;

import java.util.List;

public interface TagSettingUiHandlers extends UiHandlers {
    void saveTags(List<String> tags);
}
