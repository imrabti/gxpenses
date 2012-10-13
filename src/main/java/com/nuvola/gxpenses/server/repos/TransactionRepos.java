package com.nuvola.gxpenses.server.repos;

import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.type.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransactionRepos extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByAccountIdAndDateBetweenAndType(Long accountId, Date from, Date to, TransactionType type,
                                                           Pageable pageable);

    Page<Transaction> findByAccountIdAndDateBetween(Long accountId, Date from, Date to, Pageable pageable);

    List<Transaction> findByAccountId(Long accountId);

    @Query("select t.payee from Transaction t where t.account.user.id = ?1")
    List<String> findAllPayeeByUserId(Long userId);

    @Query("select sum(t.amount) from Transaction t where t.account.id = ?1 and t.date between ?2 and ?3")
    Double totalByAccountAndDate(Long accountId, Date from, Date to);

    @Query("select sum(t.amount) from Transaction t where t.account.id = ?1 " +
            "and t.type = ?2 and t.date between ?3 and ?4")
    Double totalByAccountAndTypeAndDate(Long accountId, TransactionType type, Date from, Date to);
}
