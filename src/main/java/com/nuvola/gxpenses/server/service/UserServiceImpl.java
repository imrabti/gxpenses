package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.server.repos.TagRepos;
import com.nuvola.gxpenses.server.repos.TransactionRepos;
import com.nuvola.gxpenses.server.repos.UserRepos;
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
    public List<String> findAllPayeeForUser(Long userId) {
        return transactionRepos.findAllPayeeByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllTagsForUser(Long userId) {
        return tagRepos.findAllTagByUserId(userId);
    }

    @Override
    public void updateUserTags(Long userId, List<String> tags) {
        User user = userRepos.findOne(userId);
        for (String tag : tags) {
            Tag newTag = new Tag();
            newTag.setValue(tag);
            newTag.setUser(user);
            tagRepos.save(newTag);
        }
    }

}
