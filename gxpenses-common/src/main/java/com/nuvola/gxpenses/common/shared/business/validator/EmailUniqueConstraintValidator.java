package com.nuvola.gxpenses.common.shared.business.validator;

import com.nuvola.gxpenses.server.repos.UserRepos;
import com.nuvola.gxpenses.server.security.SecurityContextProvider;
import com.nuvola.gxpenses.server.util.ApplicationContextProvider;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUnique, String> {
    private UserRepos usersRepos;
    private SecurityContextProvider securityContext;
    private String currentUserMail;

    @Override
    public void initialize(EmailUnique constraint) {
        usersRepos = ApplicationContextProvider.getContext().getBean(UserRepos.class);
        securityContext = ApplicationContextProvider.getContext().getBean(SecurityContextProvider.class);
        currentUserMail = securityContext.getCurrentUser().getEmail();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        if(value == null || value.trim().equals("")) {
            return true;
        }

        if (currentUserMail.equals(value.toLowerCase().trim()))
            return true;
        else {
            if (usersRepos.findByEmail(value) == null) {
                return true;
            } else {
                return false;
            }
        }
    }
}

