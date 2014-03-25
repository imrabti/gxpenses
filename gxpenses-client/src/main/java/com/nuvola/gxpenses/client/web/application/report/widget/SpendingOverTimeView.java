package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class SpendingOverTimeView extends ViewWithUiHandlers<SpendingOverTimeUiHandlers>
        implements SpendingOverTimePresenter.MyView {
    public interface Binder extends UiBinder<Widget, SpendingOverTimeView> {
    }

    @Inject
    SpendingOverTimeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}