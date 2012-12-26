package com.nuvola.gxpenses.server.business.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nuvola.gxpenses.server.security.SecurityContextProvider;
import com.nuvola.gxpenses.server.util.ApplicationContextProvider;

public class OldPasswordCheckConstraintValidator implements ConstraintValidator<OldPasswordCheck, String> {
    private SecurityContextProvider securityContext;
    private String currentPassword;

    @Override
    public void initialize(OldPasswordCheck constraint) {
        securityContext = ApplicationContextProvider.getContext().getBean(SecurityContextProvider.class);
        currentPassword = securityContext.getCurrentUser().getPassword();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext validatorContext) {
        if(value == null || value.trim().equals("")) {
            return true;
        }

        if (currentPassword.equals(value)) {
            return true;
        } else {
            return false;
        }
    }
}

