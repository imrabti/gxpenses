package com.nuvola.gxpenses.common.shared.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class BudgetElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tag;
    private Double amount;
    @Transient
    private Double consumedAmount;
    @Transient
    private Double leftAmount;
    @ManyToOne
    private Budget budget;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getConsumedAmount() {
        return consumedAmount;
    }

    public void setConsumedAmount(Double consumedAmount) {
        this.consumedAmount = consumedAmount;
    }

    public Double getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(Double leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
