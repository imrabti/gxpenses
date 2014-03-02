package com.nuvola.gxpenses.client.rest;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;

public interface TagService extends RestService {
    @GET
    RestAction<List<String>> findAllTags();

    @POST
    RestAction<Void> createTags(List<String> tags);
}
