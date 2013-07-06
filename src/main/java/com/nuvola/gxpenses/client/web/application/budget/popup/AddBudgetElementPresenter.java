package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.request.BudgetRequest;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.proxy.BudgetElementProxy;
import com.nuvola.gxpenses.client.request.proxy.BudgetProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;

import java.util.List;

public class AddBudgetElementPresenter extends PresenterWidget<AddBudgetElementPresenter.MyView>
        implements AddBudgetElementUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<AddBudgetElementUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(BudgetElementProxy budgetElement);

        void setData(List<BudgetElementProxy> budgetElements);
    }

    private final GxpensesRequestFactory requestFactory;
    private final MessageBundle messageBundle;

    private BudgetProxy selectedBudget;
    private Widget relativeTo;
    private BudgetRequest currentContext;

    @Inject
    public AddBudgetElementPresenter(final EventBus eventBus, final MyView view,
                                     final GxpensesRequestFactory requestFactory,
                                     final MessageBundle messageBundle) {
        super(eventBus, view);

        this.requestFactory = requestFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void addNewBudgetElement(BudgetElementProxy budgetElement) {
        currentContext.createBudgetElement(selectedBudget.getId(), budgetElement).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                BudgetElementsChangedEvent.fire(this);
                GlobalMessageEvent.fire(this, messageBundle.budgetElementAdded());
                prepareNewBudgetElement();
                fireLoadBudgetElementById();
            }
        });
    }

    @Override
    public void removeBudgetElement(BudgetElementProxy budgetElement) {
        Boolean decision = Window.confirm(messageBundle.budgetElementConf());
        if (decision) {
            requestFactory.budgetService().removeBudgetElement(budgetElement.getId()).fire(new ReceiverImpl<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
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

    public void setSelectedBudget(BudgetProxy selectedBudget) {
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
        currentContext = requestFactory.budgetService();
        BudgetElementProxy newBudgetElement = currentContext.create(BudgetElementProxy.class);
        newBudgetElement.setBudget(selectedBudget);
        getView().edit(newBudgetElement);
    }

    private void fireLoadBudgetElementById() {
        requestFactory.budgetService().findAllBudgetElementsByBudget(selectedBudget.getId())
                .fire(new ReceiverImpl<List<BudgetElementProxy>>() {
            @Override
            public void onSuccess(List<BudgetElementProxy> budgetElements) {
                getView().setData(budgetElements);
            }
        });
    }
}
