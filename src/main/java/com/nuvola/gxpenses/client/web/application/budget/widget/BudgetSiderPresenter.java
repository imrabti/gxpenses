package com.nuvola.gxpenses.client.web.application.budget.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class BudgetSiderPresenter extends PresenterWidget<BudgetSiderPresenter.MyView>
        implements BudgetSiderUiHandlers {

    public interface MyView extends View, HasUiHandlers<BudgetSiderUiHandlers> {
    }

    @Inject
    public BudgetSiderPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }

}
