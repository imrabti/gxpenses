package com.nuvola.gxpenses.client.web.application;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.web.application.budget.BudgetModule;
import com.nuvola.gxpenses.client.web.application.renderer.TokenCellFactory;
import com.nuvola.gxpenses.client.web.application.report.ReportModule;
import com.nuvola.gxpenses.client.web.application.setting.SettingModule;
import com.nuvola.gxpenses.client.web.application.transaction.TransactionModule;
import com.nuvola.gxpenses.client.web.application.widget.HeaderPresenter;
import com.nuvola.gxpenses.client.web.application.widget.HeaderView;
import com.nuvola.gxpenses.client.web.application.widget.SiderHolderPresenter;
import com.nuvola.gxpenses.client.web.application.widget.SiderHolderView;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new TransactionModule());
        install(new BudgetModule());
        install(new SettingModule());
        install(new ReportModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        bindSingletonPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class,
                HeaderView.class);
        bindSingletonPresenterWidget(SiderHolderPresenter.class, SiderHolderPresenter.MyView.class,
                SiderHolderView.class);

        install(new GinFactoryModuleBuilder().build(TokenCellFactory.class));
    }
}
