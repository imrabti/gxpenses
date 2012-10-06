package com.nuvola.gxpenses.client.web.application.setting;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewImpl;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class SettingView extends ViewImpl implements SettingPresenter.MyView {

    public interface Binder extends UiBinder<Widget, SettingView> {
    }

    @UiField
    SimplePanel contentPanel;
    @UiField
    HTML errors;

    @Inject
    public SettingView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        errors.setVisible(false);
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (content != null) {
            if (slot == SettingPresenter.TYPE_SetMainContent) {
                contentPanel.setWidget(content);
            }
        }
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
