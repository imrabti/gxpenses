package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.BootStrapper;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.BudgetService;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.shared.domaine.Budget;
import com.nuvola.gxpenses.shared.type.FrequencyType;

public class AddBudgetPresenter extends PresenterWidget<AddBudgetPresenter.MyView> implements AddBudgetUiHandler {

    public interface MyView extends PopupView, HasUiHandlers<AddBudgetUiHandler> {
        void showRelativeTo(Widget widget);

        void edit(Budget budget);
    }

    private final BudgetService budgetService;
    private final BootStrapper bootStrapper;
    private final MessageBundle messageBundle;

    private Widget relativeTo;

    @Inject
    public AddBudgetPresenter(final EventBus eventBus, final MyView view,
                              final BudgetService budgetService,
                              final BootStrapper bootStrapper,
                              final MessageBundle messageBundle) {
        super(eventBus, view);

        this.budgetService = budgetService;
        this.bootStrapper = bootStrapper;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveBudget(Budget budget) {
        budgetService.createBudget(budget, new MethodCallbackImpl<Void>() {
            @Override
            public void handleSuccess(Void aVoid) {
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
        super.onReveal();

        Budget newBudget = new Budget();
        newBudget.setPeriodicity(FrequencyType.MONTH);
        newBudget.setUser(bootStrapper.getCurrentUser());
        getView().edit(newBudget);
        getView().showRelativeTo(relativeTo);
    }

}
