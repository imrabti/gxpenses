package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.server.repos.AccountRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.server.util.DateUtils;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepos accountRepos;
    @Autowired
    private TransactionRepos transactionRepos;

    @Override
    public void createAccount(Account account) {
        if (account.getBalance() == null) {
            account.setBalance(0d);
        }
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
    public List<Account> findAllAccountsByUserId(Long userId) {
        return accountRepos.findByUserId(userId, new Sort("name"));
    }

    @Override
    @Transactional(readOnly = true)
    public Double totalAmountByAccountAndPeriodAndType(Long accountId, PeriodType periodeFilter, TransactionType type) {
        Date startDate = DateUtils.getStartDate(periodeFilter, new Date());
        Date endDate = DateUtils.getEndDate(periodeFilter, new Date());

        List<Transaction> transactions;
        if (type == TransactionType.ALL) {
            transactions = transactionRepos.findByAccountIdAndDateBetween(accountId, startDate, endDate);
        } else {
            transactions = transactionRepos.findByAccountIdAndDateBetweenAndType(accountId, startDate, endDate, type);
        }

        double totalAmount = 0d;
        for (Transaction transaction : transactions) {
            int multiplier = (transaction.getType() == TransactionType.EXPENSE) ? -1 : 1;
            totalAmount += transaction.getAmount() * multiplier;
        }

        return totalAmount;
    }

}
