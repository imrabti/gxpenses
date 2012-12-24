package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.request.BudgetRequest;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.proxy.BudgetProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.shared.type.FrequencyType;

public class AddBudgetPresenter extends PresenterWidget<AddBudgetPresenter.MyView> implements AddBudgetUiHandler {
    public interface MyView extends PopupView, HasUiHandlers<AddBudgetUiHandler> {
        void showRelativeTo(Widget widget);

        void edit(BudgetProxy budget);
    }

    private final GxpensesRequestFactory requestFactory;
    private final MessageBundle messageBundle;

    private Widget relativeTo;
    private BudgetRequest currentContext;

    @Inject
    public AddBudgetPresenter(final EventBus eventBus, final MyView view,
                              final GxpensesRequestFactory requestFactory,
                              final MessageBundle messageBundle) {
        super(eventBus, view);

        this.requestFactory = requestFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveBudget(BudgetProxy budget) {
        currentContext.createBudget(budget).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
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

        currentContext = requestFactory.budgetService();
        BudgetProxy newBudget = currentContext.create(BudgetProxy.class);
        newBudget.setPeriodicity(FrequencyType.MONTH);
        getView().edit(newBudget);
        getView().showRelativeTo(relativeTo);
    }
}
