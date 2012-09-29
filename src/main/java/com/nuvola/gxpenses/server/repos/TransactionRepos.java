package com.nuvola.gxpenses.server.repos;

import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.type.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepos extends JpaRepository<Transaction, Long> {
	Page<Transaction> findByAccountIdAndDateBetweenAndType(Long accountId, Date from, Date to, TransactionType type,
                                                           Pageable pageable);

    Page<Transaction> findByAccountIdAndDateBetween(Long accountId, Date from, Date to, Pageable pageable);

    List<Transaction> findByAccountIdAndDateBetweenAndType(Long accountId, Date from, Date to, TransactionType type);

    List<Transaction> findByAccountIdAndDateBetween(Long accountId, Date from, Date to);
	
	List<Transaction> findByAccountId(Long accountId);
}
