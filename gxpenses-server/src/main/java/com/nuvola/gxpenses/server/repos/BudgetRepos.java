package com.nuvola.gxpenses.server.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuvola.gxpenses.common.shared.business.Budget;

public interface BudgetRepos extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(Long userId);
}
