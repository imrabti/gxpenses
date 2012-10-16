package com.nuvola.gxpenses.client.rest;

import com.nuvola.gxpenses.shared.domaine.BudgetElement;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

public interface BudgetElementService extends RestService {
    @POST
    @Path("/element/{id}")
    void createBudgetElement(@PathParam("id") String id, BudgetElement budgetElement, MethodCallback<Void> callback);

    @DELETE
    @Path("/element")
    void removeBudgetElement(BudgetElement budgetElement, MethodCallback<Void> callback);

    @GET
    @Path("/element/{id}/{period}")
    void getBudgetElements(@PathParam("id") String id, @PathParam("period") String period,
                           MethodCallback<List<BudgetElement>> callback);
}
