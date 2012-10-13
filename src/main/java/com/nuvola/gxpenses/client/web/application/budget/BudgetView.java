package com.nuvola.gxpenses.client.web.application.budget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class BudgetView extends ViewWithUiHandlers<BudgetUiHandlers> implements BudgetPresenter.MyView {

    public interface Binder extends UiBinder<Widget, BudgetView> {
    }

    @Inject
    public BudgetView(final Binder uiBinder,
                      final UiHandlersStrategy<BudgetUiHandlers> uiHandlers) {
        super(uiHandlers);

        //Init The UI Binder
        initWidget(uiBinder.createAndBindUi(this));
    }

}
