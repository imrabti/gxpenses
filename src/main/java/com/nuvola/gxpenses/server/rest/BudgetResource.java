package com.nuvola.gxpenses.server.rest;

import com.nuvola.gxpenses.server.service.BudgetService;
import com.nuvola.gxpenses.server.util.SecurityContext;
import com.nuvola.gxpenses.shared.domaine.Budget;
import com.nuvola.gxpenses.shared.domaine.BudgetElement;
import com.nuvola.gxpenses.shared.domaine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Path("/budget")
public class BudgetResource extends Resource {

    @Autowired
    private BudgetService budgetService;
    @Autowired
    private SecurityContext securityContext;

    @POST
    public Response createBudget(Budget budget) {
        budgetService.createBudget(budget);
        return Response.ok().build();
    }

    @GET
    @Path("/{period}")
    public List<Budget> getBudgets(@PathParam("period") String period) throws ParseException {
        User currentUser = securityContext.getCurrentUser();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return budgetService.findAllBudgetsByUserId(currentUser.getId(), dateFormat.parse(period));
    }

    @POST
    @Path("/element/{id}")
    public Response createBudgetElement(@PathParam("id") Long id, BudgetElement budgetElement) {
        budgetService.createBudgetElement(id, budgetElement);
        return Response.ok().build();
    }

    @DELETE
    @Path("/element")
    public Response removeBudgetElement(BudgetElement budgetElement) {
        budgetService.removeBudgetElement(budgetElement);
        return Response.ok().build();
    }

    @GET
    @Path("/element/{id}/{period}")
    public List<BudgetElement> getBudgetElements(@PathParam("id") Long id,
                                                 @PathParam("period") String period) throws ParseException {
        User currentUser = securityContext.getCurrentUser();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return budgetService.findAllBudgetElementsByBudget(id, currentUser.getId(), dateFormat.parse(period));
    }

}
