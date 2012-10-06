package com.nuvola.gxpenses.server.util;

import com.nuvola.gxpenses.server.repos.UserRepos;
import com.nuvola.gxpenses.shared.domaine.User;
import com.nuvola.gxpenses.shared.type.CurrencyType;
import com.nuvola.gxpenses.shared.type.PaginationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class SecurityContextImpl implements SecurityContext {

    private static final String EMAIL = "imrabti@gmail.com";

    @Autowired
    private UserRepos userRepos;

    @Override
    @Transactional
    public User getCurrentUser() {
        User currentUser = userRepos.findByEmail(EMAIL);

        if (currentUser == null) {
            currentUser = setupTestUser();
        }

        return currentUser;
    }

    private User setupTestUser() {
        User testUser = new User();
        testUser.setUserName("imrabti");
        testUser.setEmail(EMAIL);
        testUser.setFirstName("Mrabti");
        testUser.setLastName("Idriss");
        testUser.setCurrency(CurrencyType.MAD);
        testUser.setPageSize(PaginationType.PAGE_40);
        testUser.setDateCreation(new Date());

        return userRepos.save(testUser);
    }

}
