/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.server.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuvola.gxpenses.common.shared.business.Account;
import com.nuvola.gxpenses.common.shared.business.Transaction;
import com.nuvola.gxpenses.common.shared.type.TransactionType;
import com.nuvola.gxpenses.server.repos.AccountRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.server.security.SecurityContextProvider;

@Service
@Transactional
@Secured({ "ROLE_USER" })
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepos accountRepos;
    @Autowired
    private TransactionRepos transactionRepos;
    @Autowired
    private SecurityContextProvider securityContext;

    @Override
    public void createAccount(Account account) {
        if (account.getBalance() == null) {
            account.setBalance(0d);
        }
        account.setUser(securityContext.getCurrentUser());
        account = accountRepos.save(account);

        Transaction initialTransaction = new Transaction();
        initialTransaction.setAmount(account.getBalance());
        initialTransaction.setType(TransactionType.INCOME);
        initialTransaction.setDate(new Date());
        initialTransaction.setPayee("Initial Transaction");
        initialTransaction.setAccount(account);
        transactionRepos.save(initialTransaction);
    }

    @Override
    public void removeAccount(Long accountId) {
        List<Transaction> transactions = transactionRepos.findByAccountId(accountId);
        Account account = accountRepos.findOne(accountId);
        transactionRepos.delete(transactions);
        accountRepos.delete(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAllAccountsByUserId() {
        return accountRepos.findByUserId(securityContext.getCurrentUser().getId(), new Sort("name"));
    }
}
