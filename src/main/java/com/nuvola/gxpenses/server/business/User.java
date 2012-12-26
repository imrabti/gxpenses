package com.nuvola.gxpenses.server.business;

import com.nuvola.gxpenses.server.business.validator.EmailFormat;
import com.nuvola.gxpenses.server.business.validator.EmailUnique;
import com.nuvola.gxpenses.server.business.validator.UsernameUnique;
import com.nuvola.gxpenses.shared.type.CurrencyType;
import com.nuvola.gxpenses.shared.type.PaginationType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @UsernameUnique(message = "Username: already exist")
    @NotNull(message = "Username: can't be blank")
    private String userName;
    @EmailUnique(message = "Email: address already exist")
    @EmailFormat(message = "Email: format error")
    @NotNull(message = "Email: can't be blank")
    private String email;
    @NotNull(message = "Password: can't be blank")
    private String password;
    @Enumerated
    private CurrencyType currency;
    private String firstName;
    private String lastName;
    private Date dateCreation;
    @Enumerated
    private PaginationType pageSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
