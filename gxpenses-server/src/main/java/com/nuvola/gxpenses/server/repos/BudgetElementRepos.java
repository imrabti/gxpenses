package com.nuvola.gxpenses.server.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuvola.gxpenses.common.shared.business.BudgetElement;

public interface BudgetElementRepos extends JpaRepository<BudgetElement, Long> {
    List<BudgetElement> findByBudgetId(Long budgetId);
}
