package com.nuvola.gxpenses.client.web.welcome.entrypoint.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;

public class LoginView {
    interface LoginViewUiBinder extends UiBinder<HTMLPanel, LoginView> {
    }

    private static LoginViewUiBinder ourUiBinder = GWT.create(LoginViewUiBinder.class);

    public LoginView() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    }
}
