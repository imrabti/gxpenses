package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

import java.util.List;

public interface AccountService {
    void createAccount(Account account);

    void removeAccount(Long accountId);

    List<Account> findAllAccountsByUserId(Long userId);

    Double totalAmountByAccountAndPeriodAndType(Long accountId, PeriodType periodeFilter, TransactionType type);
}
