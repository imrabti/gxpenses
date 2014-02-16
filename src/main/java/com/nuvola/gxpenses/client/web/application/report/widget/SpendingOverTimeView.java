package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class SpendingOverTimeView extends ViewWithUiHandlers<SpendingOverTimeUiHandlers>
        implements SpendingOverTimePresenter.MyView {
    public interface Binder extends UiBinder<Widget, SpendingOverTimeView> {
    }

    @Inject
    public SpendingOverTimeView(final Binder uiBinder,
                                final UiHandlersStrategy<SpendingOverTimeUiHandlers> uiHandlers) {
        super(uiHandlers);

        initWidget(uiBinder.createAndBindUi(this));
    }
}