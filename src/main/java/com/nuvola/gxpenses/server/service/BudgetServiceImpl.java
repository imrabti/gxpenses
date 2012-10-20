package com.nuvola.gxpenses.server.service;

import com.google.common.base.Objects;
import com.nuvola.gxpenses.server.repos.AccountRepos;
import com.nuvola.gxpenses.server.repos.BudgetElementRepos;
import com.nuvola.gxpenses.server.repos.BudgetRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.server.util.DateUtils;
import com.nuvola.gxpenses.shared.domaine.Budget;
import com.nuvola.gxpenses.shared.domaine.BudgetElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepos budgetRepos;
    @Autowired
    private BudgetElementRepos budgetElementRepos;
    @Autowired
    private TransactionRepos transactionRepos;
    @Autowired
    private AccountRepos accountRepos;

    @Override
    public void createBudget(Budget budget) {
        budgetRepos.save(budget);
    }

    @Override
    public void createBudgetElement(Long budgetId, BudgetElement element) {
        Budget budget = budgetRepos.findOne(budgetId);
        if(element.getAmount() == null) {
            element.setAmount(0d);
        }

        element.setBudget(budget);
        budgetElementRepos.save(element);
    }

    @Override
    public void removeBudgetElement(BudgetElement element) {
        budgetElementRepos.delete(element);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Budget> findAllBudgetsByUserId(Long userId, Date period) {
        List<Budget> budgets = budgetRepos.findByUserId(userId);

        for (Budget budget : budgets) {
            Double totalConsumed = 0d;
            Double totalAllowed = 0d;

            List<BudgetElement> budgetElements = findAllBudgetElementsByBudget(budget.getId(), userId, period);
            for (BudgetElement budgetElement : budgetElements) {
                totalConsumed += budgetElement.getConsumedAmount();
                totalAllowed += budgetElement.getAmount();
            }

            budget.setTotalAllowed(totalAllowed);
            budget.setTotalConsumed(totalConsumed);
            budget.setPercentageConsumed((int)((totalConsumed / totalAllowed) * 100));
        }

        return budgets;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BudgetElement> findAllBudgetElementsByBudget(Long budgetId) {
        return budgetElementRepos.findByBudgetId(budgetId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BudgetElement> findAllBudgetElementsByBudget(Long budgetId, Long userId, Date period) {
        Budget budget = budgetRepos.findOne(budgetId);
        List<BudgetElement> budgetElements = budgetElementRepos.findByBudgetId(budget.getId());
        List<Long> accounts = accountRepos.findAccountIdByUserId(userId);
        Date startDate = DateUtils.getStartDateFrequency(budget.getPeriodicity(), period);
        Date endDate = DateUtils.getLastDateFrequency(budget.getPeriodicity(), period);

        for (BudgetElement budgetElement : budgetElements) {
            Double consumedAmount = transactionRepos.totalByTagAndAccountsAndDate("%" + budgetElement.getTag() + "%",
                    accounts, startDate, endDate);
            budgetElement.setConsumedAmount(Math.abs(Objects.firstNonNull(consumedAmount, 0d)));
            budgetElement.setLeftAmount(budgetElement.getAmount() - budgetElement.getConsumedAmount());
        }

        return budgetElements;
    }

}
