package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.server.business.Account;
import com.nuvola.gxpenses.server.business.Transaction;
import com.nuvola.gxpenses.server.dto.DataPage;
import com.nuvola.gxpenses.server.dto.PagedTransactions;
import com.nuvola.gxpenses.server.dto.TransactionFilter;
import com.nuvola.gxpenses.server.dto.TransferTransaction;
import com.nuvola.gxpenses.server.repos.AccountRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.server.util.DateUtils;
import com.nuvola.gxpenses.shared.type.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

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
        accountRepos.updateAccountBalance(transaction.getAccount().getId(), transaction.getAmount());
    }

    @Override
    public void removeTransaction(Long transactionId) {
        Transaction transaction = transactionRepos.findOne(transactionId);
        if (transaction.getDestTransaction() != null) {
            accountRepos.updateAccountBalanceInv(transaction.getDestTransaction().getAccount().getId(),
                    transaction.getAmount());
            transactionRepos.delete(transaction.getDestTransaction());
        }

        accountRepos.updateAccountBalanceInv(transaction.getAccount().getId(), transaction.getAmount());
        transactionRepos.delete(transaction);
    }

    @Override
    public void createNewTransferTransaction(TransferTransaction transfer) {
        if ((transfer.getSourceAccount() != null) && (transfer.getTargetAccount() != null)) {
            if (!(transfer.getSourceAccount().equals(transfer.getTargetAccount()) && (transfer.getAmount() > 0d))) {
                Account sourceAccount = accountRepos.findOne(transfer.getSourceAccount().getId());
                Account destinationAccount = accountRepos.findOne(transfer.getTargetAccount().getId());
                String payeeStr = "Transfert from " + sourceAccount.getName() + " to " + destinationAccount.getName();

                Transaction sourceTrans = new Transaction();
                sourceTrans.setPayee(payeeStr);
                sourceTrans.setType(TransactionType.EXPENSE);
                sourceTrans.setDate(new Date());
                sourceTrans.setAmount(-1 * transfer.getAmount());
                sourceTrans.setAccount(sourceAccount);
                sourceTrans = transactionRepos.save(sourceTrans);

                Transaction destTrans = new Transaction();
                destTrans.setPayee(payeeStr);
                destTrans.setType(TransactionType.INCOME);
                destTrans.setDate(new Date());
                destTrans.setAmount(1 * transfer.getAmount());
                destTrans.setAccount(destinationAccount);
                destTrans = transactionRepos.save(destTrans);

                sourceTrans.setDestTransaction(destTrans);
                destTrans.setDestTransaction(sourceTrans);

                accountRepos.updateAccountBalance(transfer.getSourceAccount().getId(), sourceTrans.getAmount());
                accountRepos.updateAccountBalance(transfer.getTargetAccount().getId(), destTrans.getAmount());
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PagedTransactions findByAccountAndDateAndType(TransactionFilter filter, DataPage dataPageRequest) {
        Date startDate = DateUtils.getStartDate(filter.getPeriod(), new Date());
        Date endDate = DateUtils.getEndDate(filter.getPeriod(), new Date());
        PageRequest page = new PageRequest(dataPageRequest.getPageNumber(), dataPageRequest.getLength(),
                new Sort(Sort.Direction.DESC, "date"));

        Page<Transaction> transactions;
        if (filter.getType() == TransactionType.ALL) {
            transactions = transactionRepos.findByAccountIdAndDateBetween(filter.getAccountId(),
                    startDate, endDate, page);
        } else {
            transactions = transactionRepos.findByAccountIdAndDateBetweenAndType(filter.getAccountId(),
                    startDate, endDate, filter.getType(), page);
        }

        return new PagedTransactions(transactions.getContent(), (int) transactions.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Double totalAmountByAccountAndPeriodAndType(TransactionFilter filter) {
        Date startDate = DateUtils.getStartDate(filter.getPeriod(), new Date());
        Date endDate = DateUtils.getEndDate(filter.getPeriod(), new Date());

        if (filter.getType() == TransactionType.ALL) {
            return transactionRepos.totalByAccountAndDate(filter.getAccountId(), startDate, endDate);
        } else {
            return transactionRepos.totalByAccountAndTypeAndDate(filter.getAccountId(), filter.getType(),
                    startDate, endDate);
        }
    }
}
