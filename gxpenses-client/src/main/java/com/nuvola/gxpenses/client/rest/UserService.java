/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.client.rest;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.rest.shared.RestService;
import com.nuvola.gxpenses.common.shared.business.User;
import com.nuvola.gxpenses.common.shared.rest.ResourcesPath;

@Path(ResourcesPath.USER)
public interface UserService extends RestService {
    @POST
    RestAction<Void> createUser(User user);

    @PUT
    RestAction<Void> updateUser(User user);

    @Path(ResourcesPath.PASSWORD)
    PasswordService password();

    @Path(ResourcesPath.TAG)
    TagService tag();

    @Path(ResourcesPath.PAYEE)
    PayeeService payee();
}
