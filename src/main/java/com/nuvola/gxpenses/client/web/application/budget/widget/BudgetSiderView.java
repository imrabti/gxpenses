package com.nuvola.gxpenses.client.web.application.budget.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class BudgetSiderView extends ViewWithUiHandlers<BudgetSiderUiHandlers> implements BudgetSiderPresenter.MyView {

    public interface Binder extends UiBinder<Widget, BudgetSiderView> {
    }

    @Inject
    public BudgetSiderView(final Binder uiBinder,
                           final UiHandlersStrategy<BudgetSiderUiHandlers> uiHandlers) {
        super(uiHandlers);

        //Init The UI Binder
        initWidget(uiBinder.createAndBindUi(this));
    }

}
