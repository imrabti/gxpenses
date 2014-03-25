package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.BudgetService;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.Budget;
import com.nuvola.gxpenses.common.shared.business.BudgetElement;

import java.util.Date;
import java.util.List;

public class AddBudgetElementPresenter extends PresenterWidget<AddBudgetElementPresenter.MyView>
        implements AddBudgetElementUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<AddBudgetElementUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(BudgetElement budgetElement);

        void setData(List<BudgetElement> budgetElements);
    }

    private final RestDispatchAsync dispatcher;
    private final BudgetService budgetService;
    private final MessageBundle messageBundle;

    private Budget selectedBudget;
    private Widget relativeTo;

    @Inject
    AddBudgetElementPresenter(EventBus eventBus,
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
    public void addNewBudgetElement(BudgetElement budgetElement) {
        dispatcher.execute(budgetService.budgetElement(selectedBudget.getId()).createBudgetElement(budgetElement),
                new AsyncCallbackImpl<Void>() {
            @Override
            public void onReceive(Void response) {
                BudgetElementsChangedEvent.fire(this);
                GlobalMessageEvent.fire(this, messageBundle.budgetElementAdded());
                prepareNewBudgetElement();
                fireLoadBudgetElementById();
            }
        });
    }

    @Override
    public void removeBudgetElement(BudgetElement budgetElement) {
        Boolean decision = Window.confirm(messageBundle.budgetElementConf());
        if (decision) {
            Long budgetElementId = budgetElement.getId();
            dispatcher.execute(budgetService.budgetElement(selectedBudget.getId())
                    .removeBudgetElement(budgetElementId), new AsyncCallbackImpl<Void>() {
                @Override
                public void onReceive(Void response) {
                    BudgetElementsChangedEvent.fire(this);
                    GlobalMessageEvent.fire(this, messageBundle.budgetElementRemoved());
                    fireLoadBudgetElementById();
                }
            });
        }
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
        dispatcher.execute(budgetService.budgetElement(selectedBudget.getId()).findAllBudgetElements(new Date()),
                new AsyncCallbackImpl<List<BudgetElement>>() {
            @Override
            public void onReceive(List<BudgetElement> response) {
                getView().setData(response);
            }
        });
    }
}
