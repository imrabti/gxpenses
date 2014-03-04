package com.nuvola.gxpenses.client.rest;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;
import com.nuvola.gxpenses.common.shared.business.Account;
import com.nuvola.gxpenses.common.shared.rest.ResourcesPath;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path(ResourcesPath.ACCOUNT)
public interface AccountService extends RestService {
    @POST
    RestAction<Void> createAccount(Account account);

    @DELETE
    RestAction<Void> removeAccount(Long accountId);

    @GET
    RestAction<List<Account>> findAllAccounts();
}
