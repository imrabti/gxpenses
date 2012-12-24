package com.nuvola.gxpenses.client.web.application.budget.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.nuvola.gxpenses.client.request.proxy.BudgetProxy;

public class BudgetChangedEvent extends GwtEvent<BudgetChangedEvent.BudgetChangedHandler> {
    public static Type<BudgetChangedHandler> TYPE = new Type<BudgetChangedHandler>();

    public interface BudgetChangedHandler extends EventHandler {
        void onBudgetChanged(BudgetChangedEvent event);
    }

    private BudgetProxy budget;

    public BudgetChangedEvent() {
    }

    public BudgetChangedEvent(BudgetProxy budget) {
        this.budget = budget;
    }

    public BudgetProxy getBudget() {
        return budget;
    }

    @Override
    public Type<BudgetChangedHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<BudgetChangedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new BudgetChangedEvent());
    }

    public static void fire(HasHandlers source, BudgetProxy budget) {
        source.fireEvent(new BudgetChangedEvent(budget));
    }

    @Override
    protected void dispatch(BudgetChangedHandler handler) {
        handler.onBudgetChanged(this);
    }
}
