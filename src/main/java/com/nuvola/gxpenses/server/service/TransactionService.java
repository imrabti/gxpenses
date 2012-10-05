package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.dto.PagedData;
import com.nuvola.gxpenses.shared.dto.TransferTransaction;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

public interface TransactionService {
    public void createNewTransaction(Transaction transaction);

    public void removeTransaction(Long transactionId);

    public void createNewTransferTransaction(TransferTransaction transfer);

    public PagedData<Transaction> findByAccountAndDateAndType(Long accountId, PeriodType periodFilter,
                                                              TransactionType type, Integer pageNumber, Integer length);
}
