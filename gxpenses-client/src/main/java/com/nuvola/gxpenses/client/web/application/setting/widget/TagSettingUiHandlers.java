package com.nuvola.gxpenses.client.web.application.setting.widget;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;

public interface TagSettingUiHandlers extends UiHandlers {
    void saveTags(List<String> tags);
}
