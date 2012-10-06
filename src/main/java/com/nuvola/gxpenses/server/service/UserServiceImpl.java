package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.server.repos.TagRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.server.repos.UserRepos;
import com.nuvola.gxpenses.server.util.SecurityUtils;
import com.nuvola.gxpenses.shared.domaine.Tag;
import com.nuvola.gxpenses.shared.domaine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepos userRepos;
    @Autowired
    private TransactionRepos transactionRepos;
    @Autowired
    private TagRepos tagRepos;

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepos.findByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        User currentUser = userRepos.findOne(user.getId());
        currentUser.setEmail(user.getEmail());
        currentUser.setUserName(user.getUserName());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setCurrency(user.getCurrency());
        currentUser.setPageSize(user.getPageSize());
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        User user = userRepos.findOne(userId);
        user.setPassword(SecurityUtils.encodePasswordSha1(newPassword, user.getUserName()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllPayeeForUser(Long userId) {
        return transactionRepos.findAllPayeeByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllTagsForUser(Long userId) {
        return tagRepos.findAllByUserId(userId);
    }

    @Override
    public void createTags(Long userId, List<String> tags) {
        User user = userRepos.findOne(userId);
        for (String tag : tags) {
            Tag newTag = new Tag();
            newTag.setValue(tag);
            newTag.setUser(user);
            tagRepos.save(newTag);
        }
    }

    @Override
    public void updateTags(Long userId, List<String> tags) {
        List<Tag> currentTags = tagRepos.findByUserId(userId);
        for (Tag item : currentTags) {
            if (!tags.contains(item.getValue())) {
                tagRepos.delete(item);
            } else {
                tags.remove(item);
            }
        }

        if (tags.size() > 0) {
            User user = userRepos.findOne(userId);
            for (String tag : tags) {
                Tag newTag = new Tag();
                newTag.setValue(tag);
                newTag.setUser(user);
                tagRepos.save(newTag);
            }
        }
    }

}
