package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.BudgetService;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.Budget;
import com.nuvola.gxpenses.common.shared.type.FrequencyType;

public class AddBudgetPresenter extends PresenterWidget<AddBudgetPresenter.MyView> implements AddBudgetUiHandler {
    public interface MyView extends PopupView, HasUiHandlers<AddBudgetUiHandler> {
        void showRelativeTo(Widget widget);

        void edit(Budget budget);
    }

    private final RestDispatchAsync dispatcher;
    private final BudgetService budgetService;
    private final MessageBundle messageBundle;

    private Widget relativeTo;

    @Inject
    AddBudgetPresenter(EventBus eventBus,
                       MyView view,
                       RestDispatchAsync dispatcher,
                       BudgetService budgetService,
                       MessageBundle messageBundle) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.budgetService = budgetService;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveBudget(Budget budget) {
        dispatcher.execute(budgetService.createBudget(budget), new AsyncCallbackImpl<Void>() {
            @Override
            public void onReceive(Void response) {
                BudgetElementsChangedEvent.fire(this);
                GlobalMessageEvent.fire(this, messageBundle.budgetAdded());
            }
        });
    }

    public void setRelativeTo(Widget relativeTo) {
        this.relativeTo = relativeTo;
    }

    @Override
    protected void onReveal() {
        Budget newBudget = new Budget();
        newBudget.setPeriodicity(FrequencyType.MONTH);

        getView().edit(newBudget);
        getView().showRelativeTo(relativeTo);
    }
}
