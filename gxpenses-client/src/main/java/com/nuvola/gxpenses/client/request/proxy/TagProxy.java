package com.nuvola.gxpenses.client.request.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.nuvola.gxpenses.server.business.Tag;

@ProxyFor(Tag.class)
public interface TagProxy extends ValueProxy {
    Long getId();

    void setId(Long id);

    String getValue();

    void setValue(String value);

    UserProxy getUser();

    void setUser(UserProxy user);
}
