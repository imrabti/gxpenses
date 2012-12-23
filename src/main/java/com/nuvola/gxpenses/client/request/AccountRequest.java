package com.nuvola.gxpenses.client.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;
import com.nuvola.gxpenses.server.service.AccountServiceImpl;
import com.nuvola.gxpenses.server.util.SpringServiceLocator;

import java.util.List;

@Service(value = AccountServiceImpl.class, locator = SpringServiceLocator.class)
public interface AccountRequest extends RequestContext {
    Request<Void> createAccount(AccountProxy account);

    Request<Void> removeAccount(Long accountId);

    Request<List<AccountProxy>> findAllAccountsByUserId();
}
