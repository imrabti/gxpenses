package com.nuvola.gxpenses.client.rest;

import com.nuvola.gxpenses.shared.domaine.Account;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

public interface AccountService extends RestService {
    @GET
    @Path("/account")
    void getAccounts(MethodCallback<List<Account>> callback);

    @POST
    @Path("/account")
    void createAccount(Account account, MethodCallback<Void> callback);

    @DELETE
    @Path("/account/{id}")
    void removeAccount(@PathParam("id") Long accountId, MethodCallback<Void> callback);
}
