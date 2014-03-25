package com.nuvola.gxpenses.client.web.application.budget.widget;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.NoElementFoundEvent;
import com.nuvola.gxpenses.client.rest.BudgetService;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetPresenter;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.Budget;

import java.util.Date;
import java.util.List;

public class BudgetSiderPresenter extends PresenterWidget<BudgetSiderPresenter.MyView>
        implements BudgetSiderUiHandlers, BudgetElementsChangedEvent.BudgetElementsChangedHandler {
    public interface MyView extends View, HasUiHandlers<BudgetSiderUiHandlers> {
        void setData(List<Budget> budgets);

        void clearSelection();
    }

    private final RestDispatchAsync dispatcher;
    private final BudgetService budgetService;
    private final AddBudgetPresenter addBudgetPresenter;

    @Inject
    BudgetSiderPresenter(EventBus eventBus,
                         MyView view,
                         RestDispatchAsync dispatcher,
                         BudgetService budgetService,
                         AddBudgetPresenter addBudgetPresenter) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.budgetService = budgetService;
        this.addBudgetPresenter = addBudgetPresenter;

        getView().setUiHandlers(this);
    }

    @Override
    public void addNewBudget(Widget relativeTo) {
        addBudgetPresenter.setRelativeTo(relativeTo);
        addToPopupSlot(addBudgetPresenter, false);
    }

    @Override
    public void budgetSelected(Budget budget) {
        BudgetChangedEvent.fire(this, budget);
    }

    @Override
    public void onBudgetElementsChanged(BudgetElementsChangedEvent event) {
        fireLoadListBudgets();
    }

    @Override
    protected void onReveal() {
        fireLoadListBudgets();
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(BudgetElementsChangedEvent.getType(), this);
    }

    @Override
    protected void onHide() {
        getView().clearSelection();
    }

    private void fireLoadListBudgets() {
        dispatcher.execute(budgetService.findAllBudgets(new Date()), new AsyncCallbackImpl<List<Budget>>() {
            @Override
            public void onReceive(List<Budget> response) {
                getView().setData(response);
                NoElementFoundEvent.fire(this, response.size());
            }
        });
    }
}
