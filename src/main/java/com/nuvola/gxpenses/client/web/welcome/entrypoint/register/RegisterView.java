package com.nuvola.gxpenses.client.web.welcome.entrypoint.register;

import com.google.common.base.Strings;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.server.business.User;

public class RegisterView extends ViewWithUiHandlers<RegisterUiHandlers> implements RegisterPresenter.MyView {

    public interface Binder extends UiBinder<Widget, RegisterView> {
    }

    public interface Driver extends SimpleBeanEditorDriver<User, RegisterView> {
    }

    @UiField
    TextBox email;
    @UiField
    TextBox userName;
    @UiField
    PasswordTextBox password;
    @UiField
    @Ignore
    Label registrationError;

    private final MessageBundle messageBundle;
    private final Driver driver;

    @Inject
    public RegisterView(final Binder uiBinder, final MessageBundle messageBundle,
                        final UiHandlersStrategy<RegisterUiHandlers> uiHandlers,
                        final Driver driver) {
        super(uiHandlers);

        this.messageBundle = messageBundle;
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    @Override
    public void edit(User user) {
        email.setFocus(true);
        driver.edit(user);
        registrationError.setVisible(false);
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

    @UiHandler("register")
    void onRegisterClicked(ClickEvent event) {
        processRegisterAction();
    }

    @UiHandler("password")
    void onPasswordKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            processRegisterAction();
        }
    }

    private void processRegisterAction() {
        User user = get();

        if (!Strings.isNullOrEmpty(user.getEmail()) && !Strings.isNullOrEmpty(user.getUserName()) &&
                !Strings.isNullOrEmpty(user.getPassword())) {
            getUiHandlers().register(user);
            registrationError.setVisible(false);
        } else {
            registrationError.setVisible(true);
            registrationError.setText(messageBundle.registerInfoMissing());
        }
    }

}
