package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.shared.domaine.Budget;

public interface AddBudgetUiHandler extends UiHandlers {
    void saveBudget(Budget budget);
}
