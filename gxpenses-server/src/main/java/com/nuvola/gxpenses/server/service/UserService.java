/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.server.service;

import java.util.List;

import com.nuvola.gxpenses.common.shared.business.User;
import com.nuvola.gxpenses.common.shared.dto.Password;

public interface UserService {
    User findUserByEmail(String email);

    void createUser(User user);

    void updateUser(User user);

    void updatePassword(Password password);

    List<String> findAllPayeeForUser();

    List<String> findAllTagsForUser();

    void createTags(List<String> tags);

    void updateTags(List<String> tags);
}
