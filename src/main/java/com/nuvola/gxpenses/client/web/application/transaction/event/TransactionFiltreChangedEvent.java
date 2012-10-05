package com.nuvola.gxpenses.client.web.application.transaction.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

public class TransactionFiltreChangedEvent extends GwtEvent<TransactionFiltreChangedEvent.TransactionFilterChangedHandler> {

    public static Type<TransactionFilterChangedHandler> TYPE = new Type<TransactionFilterChangedHandler>();

    public interface TransactionFilterChangedHandler extends EventHandler {
        void onFilterChanged(TransactionFiltreChangedEvent event);
    }

    private PeriodType periodeFilter;
    private TransactionType typeFilter;

    public TransactionFiltreChangedEvent(PeriodType periodeFilter, TransactionType typeFilter) {
        this.periodeFilter = periodeFilter;
        this.typeFilter = typeFilter;
    }

    public PeriodType getPeriodeFilter() {
        return periodeFilter;
    }

    public TransactionType getTypeFilter() {
        return typeFilter;
    }

    @Override
    public Type<TransactionFilterChangedHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<TransactionFilterChangedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, PeriodType periodeFilter, TransactionType typeFilter) {
        source.fireEvent(new TransactionFiltreChangedEvent(periodeFilter, typeFilter));
    }

    @Override
    protected void dispatch(TransactionFilterChangedHandler handler) {
        handler.onFilterChanged(this);
    }

}
