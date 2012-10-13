package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.dto.PagedData;
import com.nuvola.gxpenses.shared.dto.TransferTransaction;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

public interface TransactionService {
    void createNewTransaction(Transaction transaction);

    void removeTransaction(Long transactionId);

    void createNewTransferTransaction(TransferTransaction transfer);

    PagedData<Transaction> findByAccountAndDateAndType(Long accountId, PeriodType periodFilter,
                                                       TransactionType type, Integer pageNumber, Integer length);

    Double totalAmountByAccountAndPeriodAndType(Long accountId, PeriodType periodFilter, TransactionType type);
}
