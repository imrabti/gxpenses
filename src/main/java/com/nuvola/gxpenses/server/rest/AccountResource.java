package com.nuvola.gxpenses.server.rest;

import com.nuvola.gxpenses.server.service.AccountService;
import com.nuvola.gxpenses.server.util.SecurityContext;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.domaine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/account")
public class AccountResource extends Resource {

    @Autowired
    private AccountService accountService;
    @Autowired
    private SecurityContext securityContext;

    @GET
    public List<Account> getAccounts() {
        User currentUser = securityContext.getCurrentUser();
        return accountService.findAllAccountsByUserId(currentUser.getId());
    }

    @POST
    public Response createAccount(Account account) {
        accountService.createAccount(account);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeAccount(@PathParam("id") Long id) {
        accountService.removeAccount(id);
        return Response.ok().build();
    }

}
