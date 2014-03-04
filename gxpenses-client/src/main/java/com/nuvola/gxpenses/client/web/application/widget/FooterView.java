package com.nuvola.gxpenses.client.web.application.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class FooterView extends Composite {
    public interface Binder extends UiBinder<Widget, FooterView> {
    }

    @Inject
    FooterView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
