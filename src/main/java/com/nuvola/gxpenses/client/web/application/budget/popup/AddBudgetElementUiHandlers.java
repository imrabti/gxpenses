package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.BudgetElementProxy;

public interface AddBudgetElementUiHandlers extends UiHandlers {
    void addNewBudgetElement(BudgetElementProxy budgetElement);

    void removeBudgetElement(BudgetElementProxy budgetElement);

    void close();
}
