package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.BudgetElementService;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.shared.domaine.Budget;
import com.nuvola.gxpenses.shared.domaine.BudgetElement;
import org.fusesource.restygwt.client.Method;

import java.util.List;

public class AddBudgetElementPresenter extends PresenterWidget<AddBudgetElementPresenter.MyView>
        implements AddBudgetElementUiHandlers {

    public interface MyView extends PopupView, HasUiHandlers<AddBudgetElementUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(BudgetElement budgetElement);

        void setData(List<BudgetElement> budgetElements);
    }

    private final BudgetElementService budgetElementService;
    private final MessageBundle messageBundle;

    private Budget selectedBudget;
    private Widget relativeTo;

    @Inject
    public AddBudgetElementPresenter(final EventBus eventBus, final MyView view,
                                     final BudgetElementService budgetElementService,
                                     final MessageBundle messageBundle) {
        super(eventBus, view);

        this.budgetElementService = budgetElementService;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void addNewBudgetElement(BudgetElement budgetElement) {
        budgetElementService.createBudgetElement(selectedBudget.getId().toString(), budgetElement,
                new MethodCallbackImpl<Void>() {
            @Override
            public void onSuccess(Method method, Void aVoid) {
                BudgetElementsChangedEvent.fire(this);
                GlobalMessageEvent.fire(this, messageBundle.budgetElementAdded());
                prepareNewBudgetElement();
                fireLoadBudgetElementById();
            }
        });
    }

    @Override
    public void removeBudgetElement(BudgetElement budgetElement) {

    }

    @Override
    public void close() {
        PopupClosedEvent.fire(this);
    }

    public void setSelectedBudget(Budget selectedBudget) {
        this.selectedBudget = selectedBudget;
    }

    public void setRelativeTo(Widget relativeTo) {
        this.relativeTo = relativeTo;
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        prepareNewBudgetElement();
        fireLoadBudgetElementById();
        getView().showRelativeTo(relativeTo);
    }

    private void prepareNewBudgetElement() {
        BudgetElement newBudgetElement = new BudgetElement();
        newBudgetElement.setBudget(selectedBudget);
        getView().edit(newBudgetElement);
    }

    private void fireLoadBudgetElementById() {
        budgetElementService.getBudgetElements(selectedBudget.getId().toString(),
                new MethodCallbackImpl<List<BudgetElement>>() {
            @Override
            public void onSuccess(Method method, List<BudgetElement> budgetElements) {
                getView().setData(budgetElements);
            }
        });
    }

}
