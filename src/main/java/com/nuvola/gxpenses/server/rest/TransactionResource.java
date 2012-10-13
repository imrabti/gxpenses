package com.nuvola.gxpenses.server.rest;

import com.nuvola.gxpenses.server.service.TransactionService;
import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.dto.PagedData;
import com.nuvola.gxpenses.shared.dto.TransactionFilter;
import com.nuvola.gxpenses.shared.dto.TransferTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Component
@Path("/transaction")
public class TransactionResource extends Resource {

    @Autowired
    private TransactionService transactionService;

    @POST
    public Response createTransaction(Transaction transaction) {
        transactionService.createNewTransaction(transaction);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeTransaction(@PathParam("id") Long id) {
        transactionService.removeTransaction(id);
        return Response.ok().build();
    }

    @POST
    @Path("/transfer")
    public Response createTransfer(TransferTransaction transfer) {
        transactionService.createNewTransferTransaction(transfer);
        return Response.ok().build();
    }

    @POST
    @Path("/filter")
    public PagedData<Transaction> getTransactions(TransactionFilter filter) {
        return transactionService.findByAccountAndDateAndType(filter.getAccountId(), filter.getPeriodFilter(),
                filter.getTypeFilter(), filter.getPageNumber(), filter.getLength());
    }

    @POST
    @Path("/total")
    public Double totalAmount(TransactionFilter filter) {
        return transactionService.totalAmountByAccountAndPeriodAndType(filter.getAccountId(),
                filter.getPeriodFilter(), filter.getTypeFilter());
    }

}
