package com.nuvola.gxpenses.client.web.application.report.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.nuvola.gxpenses.client.web.application.report.widget.ReportSiderView.ReportEnum;

public class ReportsMenuChangedEvent extends GwtEvent<ReportsMenuChangedEvent.ReportsChangedEventHandler> {
    public static Type<ReportsChangedEventHandler> TYPE = new Type<ReportsChangedEventHandler>();

    public interface ReportsChangedEventHandler extends EventHandler {
        void onReportsChanged(ReportsMenuChangedEvent event);
    }

    private ReportEnum selectedMenu;

    public ReportsMenuChangedEvent(ReportEnum selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public ReportEnum getSelectedMenu() {
        return selectedMenu;
    }

    public static Type<ReportsChangedEventHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, ReportEnum selectedMenu) {
        source.fireEvent(new ReportsMenuChangedEvent(selectedMenu));
    }

    @Override
    public Type<ReportsChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ReportsChangedEventHandler handler) {
        handler.onReportsChanged(this);
    }
}
