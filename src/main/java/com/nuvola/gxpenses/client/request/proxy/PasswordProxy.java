package com.nuvola.gxpenses.client.request.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.nuvola.gxpenses.server.dto.Password;

@ProxyFor(Password.class)
public interface PasswordProxy extends ValueProxy {
    String getOldPassword();

    void setOldPassword(String oldPassword);

    String getNewPassword();

    void setNewPassword(String newPassword);

    String getPasswordConf();

    void setPasswordConf(String passwordConf);
}
