package com.nuvola.gxpenses.client.web.application.setting.widget.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.request.proxy.PasswordProxy;
import com.nuvola.gxpenses.client.util.EditorView;

public class PasswordEditor extends Composite implements EditorView<PasswordProxy> {
    public interface Binder extends UiBinder<HTMLPanel, PasswordEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<PasswordProxy, PasswordEditor> {
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
    public void edit(PasswordProxy password) {
        driver.edit(password);
    }

    @Override
    public PasswordProxy get() {
        PasswordProxy password = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return password;
        }
    }
}
