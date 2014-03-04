package com.nuvola.gxpenses.client.gin;

import com.nuvola.gxpenses.common.shared.business.User;

public class CurrentUser {
    private User user;

    public void init(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
