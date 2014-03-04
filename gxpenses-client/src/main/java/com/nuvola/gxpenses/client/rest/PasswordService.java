package com.nuvola.gxpenses.client.rest;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;
import com.nuvola.gxpenses.common.shared.dto.Password;

import javax.ws.rs.PUT;

public interface PasswordService extends RestService {
    @PUT
    RestAction<Void> updatePassword(Password password);
}
