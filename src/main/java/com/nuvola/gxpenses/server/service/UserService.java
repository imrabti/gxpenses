package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.shared.domaine.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    void createUser(User user);

    void updateUser(User user);

    void updatePassword(Long userId, String newPassword);

    List<String> findAllPayeeForUser(Long userId);

    List<String> findAllTagsForUser(Long userId);

    void createTags(Long userId, List<String> tags);

    void updateTags(Long userId, List<String> tags);
}
