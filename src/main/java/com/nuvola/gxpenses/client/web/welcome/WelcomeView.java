package com.nuvola.gxpenses.client.web.welcome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;

public class WelcomeView {
    interface WelcomViewUiBinder extends UiBinder<HTMLPanel, WelcomeView> {
    }

    private static WelcomViewUiBinder ourUiBinder = GWT.create(WelcomViewUiBinder.class);

    public WelcomeView() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    }
}
