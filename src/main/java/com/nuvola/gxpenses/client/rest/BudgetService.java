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
}
