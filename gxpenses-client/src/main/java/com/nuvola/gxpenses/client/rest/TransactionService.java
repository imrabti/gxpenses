package com.nuvola.gxpenses.client.rest;

import com.google.web.bindery.requestfactory.shared.Request;
import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;
import com.nuvola.gxpenses.common.shared.business.Transaction;
import com.nuvola.gxpenses.common.shared.dto.PagedTransactions;
import com.nuvola.gxpenses.common.shared.dto.TransactionFilter;
import com.nuvola.gxpenses.common.shared.rest.ResourcesPath;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path(ResourcesPath.TRANSACTION)
public interface TransactionService extends RestService {
    @POST
    RestAction<Void> createNewTransaction(Transaction transaction);

    @DELETE
    RestAction<Void> removeTransaction(Long transactionId);

    @PUT
    RestAction<Void> updateTransaction(Transaction transaction);

    @Path(ResourcesPath.FILTER)
    @POST
    RestAction<PagedTransactions> findAllTransactions(TransactionFilter filter);
}
