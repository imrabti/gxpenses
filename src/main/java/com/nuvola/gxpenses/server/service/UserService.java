package com.nuvola.gxpenses.server.service;

import java.util.List;

public interface UserService {
    public List<String> findAllPayeeForUser(Long userId);

    public List<String> findAllTagsForUser(Long userId);

    public void updateUserTags(Long userId, List<String> tags);
}
