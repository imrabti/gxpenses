package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class SpendingByTagView extends ViewWithUiHandlers<SpendingByTagUiHandlers>
        implements SpendingByTagPresenter.MyView {
    public interface Binder extends UiBinder<Widget, SpendingByTagView> {
    }

    @Inject
    public SpendingByTagView(final Binder uiBinder,
                             final UiHandlersStrategy<SpendingByTagUiHandlers> uiHandlers) {
        super(uiHandlers);

        initWidget(uiBinder.createAndBindUi(this));
    }
}
