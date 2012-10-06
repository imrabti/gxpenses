package com.nuvola.gxpenses.shared.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Password implements Dto {

    private String oldPassword;
    private String newPassword;
    private String passwordConf;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
    }

}
