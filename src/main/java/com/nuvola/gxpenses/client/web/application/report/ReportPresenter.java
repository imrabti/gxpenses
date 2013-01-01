package com.nuvola.gxpenses.client.web.application.report;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.nuvola.gxpenses.client.event.SetVisibleSiderEvent;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.security.LoggedInGatekeeper;
import com.nuvola.gxpenses.client.web.application.ApplicationPresenter;
import com.nuvola.gxpenses.client.web.application.report.event.ReportsMenuChangedEvent;
import com.nuvola.gxpenses.client.web.application.report.widget.ReportSiderPresenter;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingByTagPresenter;
import com.nuvola.gxpenses.client.web.application.report.widget.SpendingOverTimePresenter;

public class ReportPresenter extends Presenter<ReportPresenter.MyView, ReportPresenter.MyProxy>
        implements ReportsMenuChangedEvent.ReportsChangedEventHandler {
    public interface MyView extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.report)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<ReportPresenter> {
    }

    public static final Object TYPE_SetMainContent = new Object();

    private final ReportSiderPresenter reportSiderPresenter;
    private final SpendingByTagPresenter spendingByTagPresenter;
    private final SpendingOverTimePresenter spendingOverTimePresenter;

    @Inject
    public ReportPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                           final ReportSiderPresenter reportSiderPresenter,
                           final SpendingByTagPresenter spendingByTagPresenter,
                           final SpendingOverTimePresenter spendingOverTimePresenter) {
        super(eventBus, view, proxy);

        this.reportSiderPresenter = reportSiderPresenter;
        this.spendingByTagPresenter = spendingByTagPresenter;
        this.spendingOverTimePresenter = spendingOverTimePresenter;
    }

    @Override
    public void onReportsChanged(ReportsMenuChangedEvent event) {
        switch (event.getSelectedMenu()) {
            case SPENDING_BY_TAG:
                setInSlot(TYPE_SetMainContent, spendingByTagPresenter);
                break;
            case SPENDING_OVER_TIME:
                setInSlot(TYPE_SetMainContent, spendingOverTimePresenter);
                break;
            default:
                setInSlot(TYPE_SetMainContent, spendingByTagPresenter);
                break;
        }
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.TYPE_SetMainContent, this);
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ReportsMenuChangedEvent.getType(), this);
        setInSlot(TYPE_SetMainContent, spendingByTagPresenter);
    }

    @Override
    protected void onReveal() {
        SetVisibleSiderEvent.fire(this, reportSiderPresenter);
    }
}
