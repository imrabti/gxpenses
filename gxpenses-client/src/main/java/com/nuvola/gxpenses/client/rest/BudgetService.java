package com.nuvola.gxpenses.client.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;
import com.nuvola.gxpenses.common.shared.business.Budget;
import com.nuvola.gxpenses.common.shared.rest.PathParameter;
import com.nuvola.gxpenses.common.shared.rest.ResourcesPath;
import com.nuvola.gxpenses.common.shared.rest.RestParameter;
import com.nuvola.gxpenses.common.shared.rest.UrlParameter;

@Path(ResourcesPath.BUDGET)
public interface BudgetService extends RestService {
    @POST
    RestAction<Void> createBudget(Budget budget);

    @GET
    RestAction<List<Budget>> findAllBudgets(@QueryParam(UrlParameter.PERIOD) Date period);

    @Path(PathParameter.ID)
    BudgetElementService budgetElement(@PathParam(RestParameter.ID) Long budgetId);
}
