package com.nuvola.gxpenses.server.repos;

import com.nuvola.gxpenses.shared.domaine.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepos extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(Long userId);
}
