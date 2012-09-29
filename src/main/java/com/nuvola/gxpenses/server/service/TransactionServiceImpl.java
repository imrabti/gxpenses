package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.server.repos.AccountRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.server.util.DateUtils;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.dto.TransferTransaction;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepos accountRepos;
    @Autowired
    private TransactionRepos transactionRepos;

    @Override
    public void createNewTransaction(Transaction transaction) {
        String tag = transaction.getTags().trim();
        if (tag.lastIndexOf(",") == 0 || tag.equals("")) {
            transaction.setTags(null);
        } else if (tag.lastIndexOf(",") == (tag.length() - 1)) {
            transaction.setTags(tag.substring(0, tag.lastIndexOf(",")));
        }

        if (transaction.getAmount() == null) {
            transaction.setAmount(0d);
        }

        if (transaction.getDate() == null) {
            transaction.setDate(new Date());
        } else {
            Calendar currentCal = Calendar.getInstance();
            Calendar transactionCal = Calendar.getInstance();
            transactionCal.setTime(transaction.getDate());
            transactionCal.set(Calendar.HOUR, currentCal.get(Calendar.HOUR));
            transactionCal.set(Calendar.MINUTE, currentCal.get(Calendar.MINUTE));
            transactionCal.set(Calendar.SECOND, currentCal.get(Calendar.SECOND));
            transaction.setDate(transactionCal.getTime());
        }
        transaction = transactionRepos.save(transaction);

        int multiplier = transaction.getType() == TransactionType.EXPENSE ? -1 : 1;
        accountRepos.updateAccountBalance(transaction.getAccount().getId(), multiplier, transaction.getAmount());
    }

    @Override
    public void removeTransaction(Long transactionId) {
        Transaction transaction = transactionRepos.findOne(transactionId);
        if (transaction.getDestTransaction() != null) {
            updateAccountBalanceInvert(transaction.getDestTransaction());
            transactionRepos.delete(transaction.getDestTransaction());
        }

        updateAccountBalanceInvert(transaction);
        transactionRepos.delete(transaction);
    }

    @Override
    public void createNewTransferTransaction(TransferTransaction transfer) {
        if ((transfer.getSourceAccount() != null) && (transfer.getTargetAccount() != null)) {
            if(!(transfer.getSourceAccount().equals(transfer.getTargetAccount()) && (transfer.getAmount() > 0d))) {
                Account sourceAccount = accountRepos.findOne(transfer.getSourceAccount());
                Account destinationAccount = accountRepos.findOne(transfer.getTargetAccount());
                String payeeStr = "Transfert from " + sourceAccount.getName() + " to " + destinationAccount.getName();

                Transaction sourceTrans = new Transaction();
                sourceTrans.setPayee(payeeStr);
                sourceTrans.setType(TransactionType.EXPENSE);
                sourceTrans.setDate(new Date());
                sourceTrans.setAmount(transfer.getAmount());
                sourceTrans.setAccount(sourceAccount);
                sourceTrans = transactionRepos.save(sourceTrans);

                Transaction destTrans = new Transaction();
                destTrans.setPayee(payeeStr);
                destTrans.setType(TransactionType.INCOME);
                destTrans.setDate(new Date());
                destTrans.setAmount(transfer.getAmount());
                destTrans.setAccount(destinationAccount);
                destTrans = transactionRepos.save(destTrans);

                sourceTrans.setDestTransaction(destTrans);
                destTrans.setDestTransaction(sourceTrans);

                accountRepos.updateAccountBalance(transfer.getSourceAccount(), -1, transfer.getAmount());
                accountRepos.updateAccountBalance(transfer.getTargetAccount(), 1, transfer.getAmount());
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findByAccountAndDateAndType(Long accountId, PeriodType periodFilter,
                                                         TransactionType type, Integer pageNumber, Integer length) {
        Date startDate = DateUtils.getStartDate(periodFilter, new Date());
        Date endDate = DateUtils.getEndDate(periodFilter, new Date());
        PageRequest page = new PageRequest(pageNumber, length, new Sort(Sort.Direction.DESC, "date"));

        List<Transaction> transactions;
        if (type == TransactionType.ALL) {
            transactions = transactionRepos.findByAccountIdAndDateBetween(accountId, startDate,
                    endDate, page).getContent();
        } else {
            transactions = transactionRepos.findByAccountIdAndDateBetweenAndType(accountId, startDate,
                    endDate, type, page).getContent();
        }

        return transactions;
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByAccountAndDateAndType(Long accountId, PeriodType periodFilter, TransactionType type) {
        Date startDate = DateUtils.getStartDate(periodFilter, new Date());
        Date endDate = DateUtils.getEndDate(periodFilter, new Date());
        PageRequest page = new PageRequest(0, 10);

        if (type == TransactionType.ALL) {
            return transactionRepos.findByAccountIdAndDateBetween(accountId, startDate,
                    endDate, page).getTotalElements();
        } else {
            return transactionRepos.findByAccountIdAndDateBetweenAndType(accountId, startDate,
                    endDate, type, page).getTotalElements();
        }
    }

    private void updateAccountBalanceInvert(Transaction transaction) {
        int multiplier = transaction.getType() == TransactionType.EXPENSE ? 1 : -1;
        accountRepos.updateAccountBalance(transaction.getAccount().getId(), multiplier, transaction.getAmount());
    }

}
