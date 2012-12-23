package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.server.business.Budget;
import com.nuvola.gxpenses.server.business.BudgetElement;

import java.util.Date;
import java.util.List;

public interface BudgetService {
    void createBudget(Budget budget);

    void createBudgetElement(Long budgetId, BudgetElement element);

    void removeBudgetElement(Long budgetElementId);

    List<Budget> findAllBudgetsByUserId(Date period);

    List<BudgetElement> findAllBudgetElementsByBudget(Long budgetId);

    List<BudgetElement> findAllBudgetElementsByBudget(Long budgetId, Date period);
}
