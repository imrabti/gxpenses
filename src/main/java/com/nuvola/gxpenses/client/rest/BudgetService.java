package com.nuvola.gxpenses.client.rest;

import com.nuvola.gxpenses.shared.domaine.Budget;
import com.nuvola.gxpenses.shared.domaine.BudgetElement;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

public interface BudgetService extends RestService {
    @POST
    @Path("/budget")
    void createBudget(Budget budget, MethodCallback<Void> callback);

    @GET
    @Path("/budget/{period}")
    void getBudgets(@PathParam("period") String period, MethodCallback<List<Budget>> callback);

    @POST
    @Path("/budget/element/{id}")
    void createBudgetElement(@PathParam("id") Long id, BudgetElement budgetElement, MethodCallback<Void> callback);

    @DELETE
    @Path("/budget/element")
    void removeBudgetElement(BudgetElement budgetElement, MethodCallback<Void> callback);

    @GET
    @Path("/budget/element/{id}/{period}")
    void getBudgetElements(@PathParam("id") Long id, @PathParam("period") String period,
                           MethodCallback<List<BudgetElement>> callback);
}
