package com.nuvola.gxpenses.common.shared.dto;

import com.nuvola.gxpenses.common.shared.business.validator.FieldMatch;
import com.nuvola.gxpenses.common.shared.business.validator.OldPasswordCheck;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@FieldMatch(first = "newPassword", second = "passwordConfirmation",
        message = "New Password and Password Confirmation doesn't match")
public class Password implements Serializable {
    @OldPasswordCheck(message = "Old Password: doesn't match the old password")
    @NotNull(message = "Old Password: can't be blank")
    private String oldPassword;
    @Size(min = 6, max = 15, message = "Password: Wrong size for password, must be at least 5 characters")
    @NotNull(message="New Password: can't be blank")
    private String newPassword;
    @NotNull(message="Password Confirmation: can't be blank")
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
