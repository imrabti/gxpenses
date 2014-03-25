/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.server.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuvola.gxpenses.common.shared.business.Tag;
import com.nuvola.gxpenses.common.shared.business.User;
import com.nuvola.gxpenses.common.shared.dto.Password;
import com.nuvola.gxpenses.common.shared.type.CurrencyType;
import com.nuvola.gxpenses.common.shared.type.PaginationType;
import com.nuvola.gxpenses.server.repos.TagRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.server.repos.UserRepos;
import com.nuvola.gxpenses.server.security.SecurityContextProvider;

@Service
@Transactional
@Secured({ "ROLE_USER" })
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private TransactionRepos transactionRepos;
    @Autowired
    private TagRepos tagRepos;
    @Autowired
    private SecurityContextProvider securityContext;

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepos.findByEmail(email);
    }

    @Override
    public void createUser(User user) {
        user.setCurrency(CurrencyType.US_DOLLAR);
        user.setPageSize(PaginationType.PAGE_40);
        user.setDateCreation(new Date());
        userRepos.save(user);
    }

    @Override
    public void updateUser(User user) {
        User currentUser = userRepos.findOne(securityContext.getCurrentUser().getId());
        currentUser.setEmail(user.getEmail());
        currentUser.setUserName(user.getUserName());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setCurrency(user.getCurrency());
        currentUser.setPageSize(user.getPageSize());
        userRepos.save(currentUser);
    }

    @Override
    public void updatePassword(Password password) {
        User user = userRepos.findOne(securityContext.getCurrentUser().getId());
        user.setPassword(password.getNewPassword());
        userRepos.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllPayeeForUser() {
        return transactionRepos.findAllPayeeByUserId(securityContext.getCurrentUser().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllTagsForUser() {
        return tagRepos.findAllByUserId(securityContext.getCurrentUser().getId());
    }

    @Override
    public void createTags(List<String> tags) {
        User user = userRepos.findOne(securityContext.getCurrentUser().getId());
        for (String tag : tags) {
            Tag newTag = new Tag();
            newTag.setValue(tag);
            newTag.setUser(user);
            tagRepos.save(newTag);
        }
    }

    @Override
    public void updateTags(List<String> tags) {
        List<Tag> currentTags = tagRepos.findByUserId(securityContext.getCurrentUser().getId());
        for (Tag item : currentTags) {
            if (!tags.contains(item.getValue())) {
                tagRepos.delete(item);
            } else {
                tags.remove(item.getValue());
            }
        }

        if (tags.size() > 0) {
            User user = userRepos.findOne(securityContext.getCurrentUser().getId());
            for (String tag : tags) {
                Tag newTag = new Tag();
                newTag.setValue(tag);
                newTag.setUser(user);
                tagRepos.save(newTag);
            }
        }
    }
}
