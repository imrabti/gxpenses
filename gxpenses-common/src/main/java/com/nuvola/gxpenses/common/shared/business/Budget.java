package com.nuvola.gxpenses.common.shared.business;

import com.nuvola.gxpenses.common.shared.type.FrequencyType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
public class Budget implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated
    private FrequencyType periodicity;
    @Transient
    private Double totalAllowed;
    @Transient
    private Double totalConsumed;
    @Transient
    private Integer percentageConsumed;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FrequencyType getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(FrequencyType periodicity) {
        this.periodicity = periodicity;
    }

    public Double getTotalAllowed() {
        return totalAllowed;
    }

    public void setTotalAllowed(Double totalAllowed) {
        this.totalAllowed = totalAllowed;
    }

    public Double getTotalConsumed() {
        return totalConsumed;
    }

    public void setTotalConsumed(Double totalConsumed) {
        this.totalConsumed = totalConsumed;
    }

    public Integer getPercentageConsumed() {
        return percentageConsumed;
    }

    public void setPercentageConsumed(Integer percentageConsumed) {
        this.percentageConsumed = percentageConsumed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
