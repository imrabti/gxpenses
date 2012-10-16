package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.BudgetElementService;

public class AddBudgetElementPresenter extends PresenterWidget<AddBudgetElementPresenter.MyView>
        implements AddBudgetElementUiHandlers {

    public interface MyView extends PopupView, HasUiHandlers<AddBudgetElementUiHandlers> {
    }

    private final BudgetElementService budgetElementService;
    private final MessageBundle messageBundle;

    @Inject
    public AddBudgetElementPresenter(final EventBus eventBus, final MyView view,
                               final BudgetElementService budgetElementService,
                               final MessageBundle messageBundle) {
        super(eventBus, view);

        this.budgetElementService = budgetElementService;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

}
