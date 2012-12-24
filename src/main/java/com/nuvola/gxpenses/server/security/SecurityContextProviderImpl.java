package com.nuvola.gxpenses.server.security;

import com.nuvola.gxpenses.server.business.User;
import com.nuvola.gxpenses.server.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SecurityContextProviderImpl implements SecurityContextProvider {

    @Autowired
    private UserRepos userRepos;

    @Override
    @Transactional
    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            String email = securityContext.getAuthentication().getName();
            return userRepos.findByEmail(email);
        }

        return null;
    }

}
