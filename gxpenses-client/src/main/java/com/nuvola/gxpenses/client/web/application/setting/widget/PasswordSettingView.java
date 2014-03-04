package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.request.proxy.PasswordProxy;
import com.nuvola.gxpenses.client.web.application.setting.widget.ui.PasswordEditor;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class PasswordSettingView extends ViewWithUiHandlers<PasswordSettingUiHandlers>
        implements PasswordSettingPresenter.MyView {
    public interface Binder extends UiBinder<Widget, PasswordSettingView> {
    }

    @UiField(provided = true)
    PasswordEditor passwordEditor;
    @UiField
    HTML errors;

    @Inject
    public PasswordSettingView(final Binder uiBinder,
                               final UiHandlersStrategy<PasswordSettingUiHandlers> uiHandlers,
                               final PasswordEditor passwordEditor) {
        super(uiHandlers);

        this.passwordEditor = passwordEditor;

        initWidget(uiBinder.createAndBindUi(this));
        errors.setVisible(false);
    }

    @UiHandler("save")
    void onSave(ClickEvent event) {
        PasswordProxy password = passwordEditor.get();
        if (password != null) {
            getUiHandlers().savePassword(password);
        }
    }

    @Override
    public void edit(PasswordProxy password) {
        passwordEditor.edit(password);
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
