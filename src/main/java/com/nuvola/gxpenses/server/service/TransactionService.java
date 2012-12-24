package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.server.business.Transaction;
import com.nuvola.gxpenses.server.dto.DataPage;
import com.nuvola.gxpenses.server.dto.PagedTransactions;
import com.nuvola.gxpenses.server.dto.TransactionFilter;
import com.nuvola.gxpenses.server.dto.TransferTransaction;

public interface TransactionService {
    void createNewTransaction(Transaction transaction);

    void removeTransaction(Long transactionId);

    void createNewTransferTransaction(TransferTransaction transfer);

    PagedTransactions findByAccountAndDateAndType(TransactionFilter filter, DataPage dataPage);

    Double totalAmountByAccountAndPeriodAndType(TransactionFilter filter);
}
