package com.nuvola.gxpenses.client.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;

public interface PayeeService extends RestService {
    @GET
    RestAction<List<String>> findAllPayee();

    @POST
    RestAction<Void> updateTags(List<String> tags);
}
