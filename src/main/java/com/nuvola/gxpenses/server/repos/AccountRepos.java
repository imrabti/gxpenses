package com.nuvola.gxpenses.server.repos;

import com.nuvola.gxpenses.shared.domaine.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepos extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId, Sort sort);

    @Query("select a.id from Account a where a.user.id = ?1")
    List<Long> findAccountIdByUserId(Long userId);

    @Modifying
    @Query("update Account a set a.balance = a.balance + ?2 where a.id = ?1")
    void updateAccountBalance(Long accountId, Double amount);

    @Modifying
    @Query("update Account a set a.balance = a.balance - ?2 where a.id = ?1")
    void updateAccountBalanceInv(Long accountId, Double amount);
}
