/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.server.repos;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nuvola.gxpenses.common.shared.business.Account;

public interface AccountRepos extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId, Sort sort);

    @Query("select a.id from Account a where a.user.id = ?1")
    List<Long> findAccountIdByUserId(Long userId);
}
