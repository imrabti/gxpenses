package com.nuvola.gxpenses.client.web.application.budget;

import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
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

        bindPresenter(BudgetPresenter.class, BudgetPresenter.MyView.class, BudgetView.class,
                BudgetPresenter.MyProxy.class);

        bindSingletonPresenterWidget(BudgetSiderPresenter.class, BudgetSiderPresenter.MyView.class,
                BudgetSiderView.class);
    }

}
