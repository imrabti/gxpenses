package com.nuvola.gxpenses.client.web.application.budget;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetElementPresenter;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetElementUiHandlers;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetElementView;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetPresenter;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetUiHandler;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetView;
import com.nuvola.gxpenses.client.web.application.budget.renderer.RemoveBudgetElementCellFactory;
import com.nuvola.gxpenses.client.web.application.budget.widget.BudgetSiderPresenter;
import com.nuvola.gxpenses.client.web.application.budget.widget.BudgetSiderUiHandlers;
import com.nuvola.gxpenses.client.web.application.budget.widget.BudgetSiderView;

public class BudgetModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(BudgetUiHandlers.class).to(BudgetPresenter.class);
        bind(BudgetSiderUiHandlers.class).to(BudgetSiderPresenter.class);
        bind(AddBudgetUiHandler.class).to(AddBudgetPresenter.class);
        bind(AddBudgetElementUiHandlers.class).to(AddBudgetElementPresenter.class);


        bindPresenter(BudgetPresenter.class, BudgetPresenter.MyView.class, BudgetView.class,
                BudgetPresenter.MyProxy.class);

        bindSingletonPresenterWidget(BudgetSiderPresenter.class, BudgetSiderPresenter.MyView.class,
                BudgetSiderView.class);
        bindSingletonPresenterWidget(AddBudgetPresenter.class, AddBudgetPresenter.MyView.class,
                AddBudgetView.class);
        bindSingletonPresenterWidget(AddBudgetElementPresenter.class, AddBudgetElementPresenter.MyView.class,
                AddBudgetElementView.class);

        install(new GinFactoryModuleBuilder().build(RemoveBudgetElementCellFactory.class));
    }
}
