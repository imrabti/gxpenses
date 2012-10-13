package com.nuvola.gxpenses.client.web.application.budget.widget;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.shared.domaine.Budget;

public interface BudgetSiderUiHandlers extends UiHandlers {
    void addNewBudget(Widget relativeTo);

    void budgetSelected(Budget budget);
}
