package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.web.application.report.event.ReportsMenuChangedEvent;

public class ReportSiderPresenter extends PresenterWidget<ReportSiderPresenter.MyView>
        implements ReportSiderUiHandlers {
    public interface MyView extends View, HasUiHandlers<ReportSiderUiHandlers> {
        void setDefaultMenu();
    }

    @Inject
    public ReportSiderPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }

    @Override
    public void changeMenu(ReportSiderView.ReportEnum menu) {
        ReportsMenuChangedEvent.fire(this, menu);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        getView().setDefaultMenu();
    }
}
