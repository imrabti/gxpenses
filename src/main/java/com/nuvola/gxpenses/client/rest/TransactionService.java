package com.nuvola.gxpenses.client.rest;

import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.dto.PagedData;
import com.nuvola.gxpenses.shared.dto.TransactionFilter;
import com.nuvola.gxpenses.shared.dto.TransferTransaction;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface TransactionService extends RestService {
    @POST
    @Path("/transaction")
    void createTransaction(Transaction transaction, MethodCallback<Void> callback);

    @DELETE
    @Path("/transaction/{id}")
    void removeTransaction(@PathParam("id") Long transactionId, MethodCallback<Void> callback);

    @POST
    @Path("/transaction/transfer")
    void createTransfer(TransferTransaction transfer, MethodCallback<Void> callback);

    @POST
    @Path("/transaction/filter")
    void getTransactions(TransactionFilter filter, MethodCallback<PagedData<Transaction>> callback);
}
