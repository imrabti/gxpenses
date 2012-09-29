package com.nuvola.gxpenses.shared.domaine;

import com.nuvola.gxpenses.shared.type.CurrencyType;
import com.nuvola.gxpenses.shared.type.PaginationType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.Date;

@Entity
public class User extends BaseEntity {

    private String userName;
    private String email;
    private String password;
    @Enumerated
    private CurrencyType currency;
    private String firstName;
    private String lastName;
    private Date dateCreation;
    @Enumerated
    private PaginationType pageSize;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public PaginationType getPageSize() {
        return pageSize;
    }

    public void setPageSize(PaginationType pageSize) {
        this.pageSize = pageSize;
    }

}
