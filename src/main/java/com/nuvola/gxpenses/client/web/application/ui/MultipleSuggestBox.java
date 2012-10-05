package com.nuvola.gxpenses.client.web.application.ui;

import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;

public class MultipleSuggestBox extends SuggestBox {

    public MultipleSuggestBox() {
    }

    public MultipleSuggestBox(SuggestOracle oracle) {
        super(oracle);
    }

    @Override
    public String getText() {
        String wholeString = super.getText();
        String lastString = wholeString;
        if (wholeString != null && !wholeString.trim().equals("")) {
            int lastComma = wholeString.trim().lastIndexOf(",");
            if (lastComma > 0) {
                lastString = wholeString.trim().substring(lastComma + 1);
            }
        }
        return lastString;
    }

    @Override
    public void setText(String text) {
        String wholeString = super.getText();
        if (text != null && text.equals("")) {
            super.setText(text);
        } else {
            if (wholeString != null) {
                int lastComma = wholeString.trim().lastIndexOf(",");
                if (lastComma > 0) {
                    wholeString = wholeString.trim().substring(0, lastComma);
                } else {
                    wholeString = "";
                }

                if (!wholeString.trim().endsWith(",")
                        && !wholeString.trim().equals("")) {
                    wholeString = wholeString + ", ";
                }

                wholeString = wholeString + text + ", ";
                super.setText(wholeString);
            }
        }
    }

}
