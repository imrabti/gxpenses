package com.nuvola.gxpenses.client.web.application.report;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class ReportView extends ViewWithUiHandlers<ReportUiHandlers> implements ReportPresenter.MyView {
    public interface Binder extends UiBinder<Widget, ReportView> {
    }

    @Inject
    public ReportView(final Binder uiBinder,
                      final UiHandlersStrategy<ReportUiHandlers> uiHandlers) {
        super(uiHandlers);

        initWidget(uiBinder.createAndBindUi(this));
    }
}
