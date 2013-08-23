package com.nuvola.gxpenses.client.web.application.report;

import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.web.application.report.widget.ReportSiderPresenter;
import com.nuvola.gxpenses.client.web.application.report.widget.ReportSiderUiHandlers;
import com.nuvola.gxpenses.client.web.application.report.widget.ReportSiderView;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingByTagPresenter;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingByTagUiHandlers;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingByTagView;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingOverTimePresenter;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingOverTimeUiHandlers;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingOverTimeView;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderView;

public class ReportModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<UiHandlersStrategy<ReportSiderUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<ReportSiderUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<SpendingByTagUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<SpendingByTagUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<SpendingOverTimeUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<SpendingOverTimeUiHandlers>>() {});

        bind(ReportSiderUiHandlers.class).to(ReportSiderPresenter.class);

        bindPresenter(ReportPresenter.class, ReportPresenter.MyView.class,
                ReportView.class, ReportPresenter.MyProxy.class);

        bindSingletonPresenterWidget(ReportSiderPresenter.class, ReportSiderPresenter.MyView.class,
                ReportSiderView.class);
        bindSingletonPresenterWidget(SpendingByTagPresenter.class, SpendingByTagPresenter.MyView.class,
                SpendingByTagView.class);
        bindSingletonPresenterWidget(SpendingOverTimePresenter.class, SpendingOverTimePresenter.MyView.class,
                SpendingOverTimeView.class);
    }
}
