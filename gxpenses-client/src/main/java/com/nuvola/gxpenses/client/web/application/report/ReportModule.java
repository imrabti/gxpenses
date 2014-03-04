package com.nuvola.gxpenses.client.web.application.report;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.web.application.report.widget.ReportSiderPresenter;
import com.nuvola.gxpenses.client.web.application.report.widget.ReportSiderView;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingByTagPresenter;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingByTagView;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingOverTimePresenter;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingOverTimeView;

public class ReportModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
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
