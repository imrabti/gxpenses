package com.nuvola.gxpenses.client.web.application.setting;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewImpl;

public class SettingView extends ViewImpl implements SettingPresenter.MyView {
    public interface Binder extends UiBinder<Widget, SettingView> {
    }

    @UiField
    SimplePanel contentPanel;

    @Inject
    public SettingView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (content != null) {
            if (slot == SettingPresenter.TYPE_SetMainContent) {
                contentPanel.setWidget(content);
            }
        }
    }
}
