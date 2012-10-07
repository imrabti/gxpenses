package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.web.application.setting.widget.ui.SettingEditor;
import com.nuvola.gxpenses.shared.domaine.User;

public class GeneralSettingView extends ViewWithUiHandlers<GeneralSettingUiHandlers>
        implements GeneralSettingPresenter.MyView {

	public interface Binder extends UiBinder<Widget, GeneralSettingView> {
    }

    @UiField(provided = true)
    SettingEditor settingEditor;

    @Inject
	public GeneralSettingView(final Binder uiBinder,
                              final UiHandlersStrategy<GeneralSettingUiHandlers> uiHandlers,
                              final SettingEditor settingEditor) {
        super(uiHandlers);
        this.settingEditor = settingEditor;

        initWidget(uiBinder.createAndBindUi(this));
	}

    @UiHandler("save")
    void onSave(ClickEvent event) {
        User user = settingEditor.get();
        if(user != null) {
            getUiHandlers().saveSetting(user);
        }
    }

    @Override
    public void edit(User user) {
        settingEditor.edit(user);
    }

}
