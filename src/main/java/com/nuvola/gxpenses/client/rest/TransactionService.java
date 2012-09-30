package com.nuvola.gxpenses.client.rest;

import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.dto.PagedData;
import com.nuvola.gxpenses.shared.dto.TransactionFilter;
import com.nuvola.gxpenses.shared.dto.TransferTransaction;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface TransactionService extends RestService {
    @POST
    @Path("/transaction")
    void createTransaction(Transaction transaction, MethodCallback<Void> callback);

    @POST
    @Path("/transaction/transfer")
    void createTransfer(TransferTransaction transfer, MethodCallback callback);

    @POST
    @Path("/transaction/filter")
    void getTransactions(TransactionFilter filer, MethodCallback<PagedData<Transaction>> callback);
}
