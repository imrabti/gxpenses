package com.nuvola.gxpenses.client.web.welcome.entrypoint.login;

import com.google.common.base.Strings;
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

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {

    public interface Binder extends UiBinder<Widget, LoginView> {
    }

    @UiField
    TextBox email;
    @UiField
    PasswordTextBox password;
    @UiField
    Label loginError;

    private final MessageBundle messageBundle;

    @Inject
    public LoginView(final Binder uiBinder, final MessageBundle messageBundle,
                     final UiHandlersStrategy<LoginUiHandlers> uiHandlers) {
        super(uiHandlers);

        this.messageBundle = messageBundle;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void displayLoginError(Boolean visible) {
        loginError.setVisible(visible);
        loginError.setText(messageBundle.wrongLoginOrPassword());
    }

    @UiHandler("login")
    void onLoginClicked(ClickEvent event) {
        processLoginAction();
    }

    @UiHandler("password")
    void onPasswordKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            processLoginAction();
        }
    }

    private void processLoginAction() {
        String typedEmail = email.getText();
        String typedPassword = password.getText();

        if (!Strings.isNullOrEmpty(typedEmail) && !Strings.isNullOrEmpty(typedPassword)) {
            getUiHandlers().login(email.getText(), password.getText());
            email.setText("");
            password.setText("");
        } else {
            loginError.setVisible(true);
            loginError.setText(messageBundle.loginPasswordRequired());
            password.setText("");
        }
    }

}
