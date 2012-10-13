package com.nuvola.gxpenses.client.web.application.setting.widget.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.shared.dto.Password;

public class PasswordEditor extends Composite implements EditorView<Password> {

    public interface Binder extends UiBinder<HTMLPanel, PasswordEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<Password, PasswordEditor> {
    }

    @UiField
    PasswordTextBox oldPassword;
    @UiField
    PasswordTextBox newPassword;
    @UiField
    PasswordTextBox passwordConf;

    private final Driver driver;

    @Inject
    public PasswordEditor(final Binder uiBinder, final Driver driver) {
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    @Override
    public void edit(Password password) {
        driver.edit(password);
    }

    @Override
    public Password get() {
        Password password = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return password;
        }
    }

}
