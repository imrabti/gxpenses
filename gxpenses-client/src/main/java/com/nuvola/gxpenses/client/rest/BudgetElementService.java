package com.nuvola.gxpenses.client.rest;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;
import com.nuvola.gxpenses.common.shared.business.BudgetElement;
import com.nuvola.gxpenses.common.shared.rest.UrlParameter;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;

public interface BudgetElementService extends RestService {
    @POST
    RestAction<Void> createBudgetElement(BudgetElement element);

    @DELETE
    RestAction<Void> removeBudgetElement(Long budgetElementId);

    @GET
    RestAction<List<BudgetElement>> findAllBudgetElements(@QueryParam(UrlParameter.PERIOD) Date period);
}
