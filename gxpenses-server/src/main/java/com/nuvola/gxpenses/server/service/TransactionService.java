package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.common.shared.business.Transaction;
import com.nuvola.gxpenses.common.shared.dto.DataPage;
import com.nuvola.gxpenses.common.shared.dto.PagedTransactions;
import com.nuvola.gxpenses.common.shared.dto.TransactionFilter;
import com.nuvola.gxpenses.common.shared.dto.TransferTransaction;

public interface TransactionService {
    void createNewTransaction(Transaction transaction);

    void removeTransaction(Long transactionId);

    void createNewTransferTransaction(TransferTransaction transfer);

    void updateTransaction(Transaction transaction);

    Transaction findByTransactionId(Long transactionId);

    PagedTransactions findByAccount(Long accountId, DataPage dataPage);

    PagedTransactions findByAccountAndDateAndType(TransactionFilter filter, DataPage dataPage);

    Double totalAmountByAccountAndPeriodAndType(TransactionFilter filter);
}
