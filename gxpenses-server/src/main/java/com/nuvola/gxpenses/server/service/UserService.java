package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.common.shared.business.User;
import com.nuvola.gxpenses.common.shared.dto.Password;

import java.util.List;

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
