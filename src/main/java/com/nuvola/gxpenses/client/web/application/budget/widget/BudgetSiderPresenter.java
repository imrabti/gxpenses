package com.nuvola.gxpenses.client.web.application.budget.widget;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.NoElementFoundEvent;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.proxy.BudgetProxy;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetPresenter;

import java.util.Date;
import java.util.List;

public class BudgetSiderPresenter extends PresenterWidget<BudgetSiderPresenter.MyView>
        implements BudgetSiderUiHandlers, BudgetElementsChangedEvent.BudgetElementsChangedHandler {
    public interface MyView extends View, HasUiHandlers<BudgetSiderUiHandlers> {
        void setData(List<BudgetProxy> budgets);

        void clearSelection();
    }

    private final GxpensesRequestFactory requestFactory;
    private final AddBudgetPresenter addBudgetPresenter;

    @Inject
    public BudgetSiderPresenter(EventBus eventBus, MyView view,
                                final GxpensesRequestFactory requestFactory,
                                final AddBudgetPresenter addBudgetPresenter) {
        super(eventBus, view);

        this.requestFactory = requestFactory;
        this.addBudgetPresenter = addBudgetPresenter;

        getView().setUiHandlers(this);
    }

    @Override
    public void addNewBudget(Widget relativeTo) {
        addBudgetPresenter.setRelativeTo(relativeTo);
        addToPopupSlot(addBudgetPresenter, false);
    }

    @Override
    public void budgetSelected(BudgetProxy budget) {
        BudgetChangedEvent.fire(this, budget);
    }

    @Override
    public void onBudgetElementsChanged(BudgetElementsChangedEvent event) {
        fireLoadListBudgets();
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        fireLoadListBudgets();
    }

    @Override
    protected void onBind() {
        super.onBind();

        addRegisteredHandler(BudgetElementsChangedEvent.getType(), this);
    }

    @Override
    protected void onHide() {
        super.onHide();

        getView().clearSelection();
    }

    private void fireLoadListBudgets() {
        requestFactory.budgetService().findAllBudgetsByUserId(new Date()).fire(new ReceiverImpl<List<BudgetProxy>>() {
            @Override
            public void onSuccess(List<BudgetProxy> budgets) {
                getView().setData(budgets);

                NoElementFoundEvent.fire(this, budgets.size());
            }
        });
    }
}
