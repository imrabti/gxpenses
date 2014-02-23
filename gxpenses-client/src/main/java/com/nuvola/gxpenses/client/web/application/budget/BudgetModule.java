package com.nuvola.gxpenses.client.web.application.budget;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
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
        bind(new TypeLiteral<UiHandlersStrategy<BudgetUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<BudgetUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<BudgetSiderUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<BudgetSiderUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<AddBudgetUiHandler>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<AddBudgetUiHandler>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<AddBudgetElementUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<AddBudgetElementUiHandlers>>() {});

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
