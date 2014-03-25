package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.web.application.setting.widget.ui.SettingEditor;
import com.nuvola.gxpenses.common.shared.business.User;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GeneralSettingView extends ViewWithUiHandlers<GeneralSettingUiHandlers>
        implements GeneralSettingPresenter.MyView {
    public interface Binder extends UiBinder<Widget, GeneralSettingView> {
    }

    @UiField(provided = true)
    SettingEditor settingEditor;
    @UiField
    HTML errors;

    @Inject
    GeneralSettingView(Binder uiBinder,
                       SettingEditor settingEditor) {
        this.settingEditor = settingEditor;

        initWidget(uiBinder.createAndBindUi(this));
        errors.setVisible(false);
    }

    @UiHandler("save")
    void onSave(ClickEvent event) {
        User user = settingEditor.get();
        if (user != null) {
            getUiHandlers().saveSetting(user);
        }
    }

    @Override
    public void edit(User user) {
        settingEditor.edit(user);
    }

    @Override
    public void showErrors(Set<ConstraintViolation<?>> violations) {
        errors.setVisible(true);
        StringBuilder builder = new StringBuilder();
        builder.append("<ul>");
        for (ConstraintViolation<?> violation : violations) {
            builder.append("<li class=\"error\">");
            builder.append(violation.getMessage());
            builder.append("</li>");
        }
        builder.append("</ul>");
        errors.setHTML(builder.toString());
    }

    @Override
    public void clearErrors() {
        errors.setHTML("");
        errors.setVisible(false);
    }
}
