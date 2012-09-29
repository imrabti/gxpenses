package com.nuvola.gxpenses.shared.domaine;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Tag extends BaseEntity {

    private String value;
    @ManyToOne
    private User user;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
