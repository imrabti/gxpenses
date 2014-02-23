package com.nuvola.gxpenses.client.web.application.budget.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BudgetElementsChangedEvent extends GwtEvent<BudgetElementsChangedEvent.BudgetElementsChangedHandler> {
    public static Type<BudgetElementsChangedHandler> TYPE = new Type<BudgetElementsChangedHandler>();

    public interface BudgetElementsChangedHandler extends EventHandler {
        void onBudgetElementsChanged(BudgetElementsChangedEvent event);
    }

    public BudgetElementsChangedEvent() {
        super();
    }

    @Override
    public Type<BudgetElementsChangedHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<BudgetElementsChangedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new BudgetElementsChangedEvent());
    }

    @Override
    protected void dispatch(BudgetElementsChangedHandler handler) {
        handler.onBudgetElementsChanged(this);
    }
}
