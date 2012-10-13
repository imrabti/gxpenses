package com.nuvola.gxpenses.client.web.application.budget.widget;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.NoElementFoundEvent;
import com.nuvola.gxpenses.client.rest.BudgetService;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetPresenter;
import com.nuvola.gxpenses.shared.domaine.Budget;
import org.fusesource.restygwt.client.Method;

import java.util.Date;
import java.util.List;

public class BudgetSiderPresenter extends PresenterWidget<BudgetSiderPresenter.MyView>
        implements BudgetSiderUiHandlers {

    public interface MyView extends View, HasUiHandlers<BudgetSiderUiHandlers> {
        void setData(List<Budget> budgets);
    }

    private final BudgetService budgetService;
    private final AddBudgetPresenter addBudgetPresenter;
    private final DateTimeFormat dateFormat;

    @Inject
    public BudgetSiderPresenter(EventBus eventBus, MyView view,
                                final BudgetService budgetService,
                                final AddBudgetPresenter addBudgetPresenter) {
        super(eventBus, view);

        this.budgetService = budgetService;
        this.addBudgetPresenter = addBudgetPresenter;
        this.dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");

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
    protected void onReveal() {
        super.onReveal();

        fireLoadListBudgets();
    }

    private void fireLoadListBudgets() {
        budgetService.getBudgets(dateFormat.format(new Date()), new MethodCallbackImpl<List<Budget>>() {
            @Override
            public void onSuccess(Method method, List<Budget> budgets) {
                getView().setData(budgets);

                NoElementFoundEvent.fire(this, budgets.size());
            }
        });
    }

}
