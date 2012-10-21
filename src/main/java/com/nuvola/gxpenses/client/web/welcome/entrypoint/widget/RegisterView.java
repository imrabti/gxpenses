package com.nuvola.gxpenses.client.web.welcome.entrypoint.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;

public class RegisterView {
    interface RegisterViewUiBinder extends UiBinder<HTMLPanel, RegisterView> {
    }

    private static RegisterViewUiBinder ourUiBinder = GWT.create(RegisterViewUiBinder.class);

    public RegisterView() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    }
}
