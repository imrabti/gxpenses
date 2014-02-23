package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.request.proxy.BudgetProxy;

public interface AddBudgetUiHandler extends UiHandlers {
    void saveBudget(BudgetProxy budget);
}
