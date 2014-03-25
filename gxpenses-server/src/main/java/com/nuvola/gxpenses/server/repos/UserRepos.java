/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.server.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuvola.gxpenses.common.shared.business.User;

public interface UserRepos extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUserName(String username);
}
