package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.shared.domaine.Budget;
import com.nuvola.gxpenses.shared.domaine.BudgetElement;

import java.util.Date;
import java.util.List;

public interface BudgetService {
    void createBudget(Budget budget);

    void createBudgetElement(Long budgetId, BudgetElement element);

    void removeBudgetElement(BudgetElement element);

    List<Budget> findAllBudgetsByUserId(Long userId, Date period);

    List<BudgetElement> findAllBudgetElementsByBudget(Long budgetId, Long userId, Date period);
}
