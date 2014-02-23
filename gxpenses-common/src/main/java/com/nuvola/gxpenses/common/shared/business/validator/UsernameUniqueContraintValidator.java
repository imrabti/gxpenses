package com.nuvola.gxpenses.common.shared.business.validator;

import com.nuvola.gxpenses.server.repos.UserRepos;
import com.nuvola.gxpenses.server.security.SecurityContextProvider;
import com.nuvola.gxpenses.server.util.ApplicationContextProvider;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniqueContraintValidator implements ConstraintValidator<UsernameUnique, String> {
    private UserRepos usersRepos;
    private SecurityContextProvider securityContext;
    private String currentUsername;

    @Override
    public void initialize(UsernameUnique constraint) {
        usersRepos = ApplicationContextProvider.getContext().getBean(UserRepos.class);
        securityContext = ApplicationContextProvider.getContext().getBean(SecurityContextProvider.class);
        currentUsername = securityContext.getCurrentUser().getUserName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext validatorContext) {
        if(value == null || value.trim().equals("")) {
            return true;
        }

        if (currentUsername.equals(value.toLowerCase().trim()))
            return true;
        else {
            if (usersRepos.findByUserName(value) == null) {
                return true;
            } else {
                return false;
            }
        }
    }
}

