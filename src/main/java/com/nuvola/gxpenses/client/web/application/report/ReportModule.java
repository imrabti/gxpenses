package com.nuvola.gxpenses.client.web.application.report;

import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class ReportModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<UiHandlersStrategy<ReportUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<ReportUiHandlers>>() {});

        bindPresenter(ReportPresenter.class, ReportPresenter.MyView.class,
                ReportView.class, ReportPresenter.MyProxy.class);
    }
}
