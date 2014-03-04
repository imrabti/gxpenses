package com.nuvola.gxpenses.server.repos;

import com.nuvola.gxpenses.common.shared.business.BudgetElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetElementRepos extends JpaRepository<BudgetElement, Long> {
    List<BudgetElement> findByBudgetId(Long budgetId);
}
