package com.nuvola.gxpenses.client.web.application.setting.widget.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.application.renderer.EnumRenderer;
import com.nuvola.gxpenses.shared.domaine.User;
import com.nuvola.gxpenses.shared.type.CurrencyType;
import com.nuvola.gxpenses.shared.type.PaginationType;

import java.util.Arrays;

public class SettingEditor extends Composite implements EditorView<User> {

    public interface Binder extends UiBinder<HTMLPanel, SettingEditor> {
    }

    public interface SettingDriver extends SimpleBeanEditorDriver<User, SettingEditor> {
    }

    @UiField
    TextBox userName;
    @UiField
    TextBox firstName;
    @UiField
    TextBox lastName;
    @UiField
    TextBox email;
    @UiField(provided = true)
    ValueListBox<CurrencyType> currency;
    @UiField(provided = true)
    ValueListBox<PaginationType> pageSize;

    private final SettingDriver driver;

    @Inject
    public SettingEditor(final Binder uiBinder, final SettingDriver driver) {
        this.driver = driver;

        currency = new ValueListBox<CurrencyType>(new EnumRenderer<CurrencyType>());
        pageSize = new ValueListBox<PaginationType>(new EnumRenderer<PaginationType>());

        currency.setAcceptableValues(Arrays.asList(CurrencyType.values()));
        pageSize.setAcceptableValues(Arrays.asList(PaginationType.values()));
        currency.setValue(CurrencyType.US_DOLLAR);
        pageSize.setValue(PaginationType.PAGE_40);

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    @Override
    public void edit(User setting) {
        driver.edit(setting);
    }

    @Override
    public User get() {
        User user = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return user;
        }
    }

}
