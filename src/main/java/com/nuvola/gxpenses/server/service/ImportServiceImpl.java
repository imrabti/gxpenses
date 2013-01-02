package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.server.repos.AccountRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.type.ImportFileType;
import com.nuvola.gxpenses.shared.type.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ImportServiceImpl implements ImportService {

    private final static Logger logger = Logger.getLogger(ImportService.class.getName());

    @Autowired
    private AccountRepos accountRepos;
    @Autowired
    private TransactionRepos transactionRepos;

    public void importFile(ImportFileType fileType, String fileContent) {

    };

    private Account importAccount() {
        Account account = new Account();
        return accountRepos.saveAndFlush(account);
    }

    private void importTransaction(Account account) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);

        transactionRepos.save(transaction);
        int multiplier = transaction.getType() == TransactionType.EXPENSE ? -1 : 1;
        accountRepos.updateAccountBalance(transaction.getAccount().getId(), multiplier, transaction.getAmount());
    }

}
