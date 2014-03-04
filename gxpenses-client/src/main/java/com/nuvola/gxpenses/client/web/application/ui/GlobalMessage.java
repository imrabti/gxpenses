package com.nuvola.gxpenses.client.web.application.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.resource.Resources;

public class GlobalMessage extends Widget implements HasText {
    private static final int MESSAGE_TIMEOUT = 10000;

    @Inject
    GlobalMessage(Resources resources) {
        setElement(DOM.createElement("div"));
        getElement().setInnerText("Hello World");
        setStyleName(getElement(), resources.generalStyleCss().globalMsgInfo());
        setVisible(false);
    }

    public void displayMessage(String message) {
        setText(message);
        setVisible(true);

        //Hide Message After MESSAGE_TIMEOUT
        Timer t = new Timer() {
            @Override
            public void run() {
                setVisible(false);
            }
        };
        t.schedule(MESSAGE_TIMEOUT);
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText(String text) {
        getElement().setInnerText(text);
    }
}
