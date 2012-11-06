package com.nuvola.gxpenses.server.rest;

import com.nuvola.gxpenses.server.security.SecurityContextProvider;
import com.nuvola.gxpenses.server.service.BudgetService;
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
@Path("/element")
public class BudgetElementResource extends Resource {

    @Autowired
    private BudgetService budgetService;
    @Autowired
    private SecurityContextProvider securityContext;

    @POST
    @Path("/{id}")
    public Response createBudgetElement(@PathParam("id") String id, BudgetElement budgetElement) {
        budgetService.createBudgetElement(Long.parseLong(id), budgetElement);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeBudgetElement(@PathParam("id") String id) {
        budgetService.removeBudgetElement(Long.parseLong(id));
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public List<BudgetElement> getBudgetElements(@PathParam("id") String id) {
        return budgetService.findAllBudgetElementsByBudget(Long.parseLong(id));
    }

    @GET
    @Path("/{id}/{period}")
    public List<BudgetElement> getBudgetElements(@PathParam("id") String id,
                                                 @PathParam("period") String period) throws ParseException {
        User currentUser = securityContext.getCurrentUser();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return budgetService.findAllBudgetElementsByBudget(Long.parseLong(id), currentUser.getId(),
                dateFormat.parse(period));
    }

}
