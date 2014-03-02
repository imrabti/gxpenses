package com.nuvola.gxpenses.client.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nuvola.gxpenses.client.request.proxy.PasswordProxy;
import com.nuvola.gxpenses.client.request.proxy.UserProxy;
import com.nuvola.gxpenses.server.service.UserServiceImpl;
import com.nuvola.gxpenses.server.util.SpringServiceLocator;

import java.util.List;

@Service(value = UserServiceImpl.class, locator = SpringServiceLocator.class)
public interface UserRequest extends RequestContext {
    Request<Void> updatePassword(PasswordProxy password);

    Request<List<String>> findAllPayeeForUser();

    Request<List<String>> findAllTagsForUser();

    Request<Void> createTags(List<String> tags);

    Request<Void> updateTags(List<String> tags);
}
