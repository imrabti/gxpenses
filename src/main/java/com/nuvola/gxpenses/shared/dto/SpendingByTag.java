package com.nuvola.gxpenses.shared.dto;

import java.io.Serializable;

public class SpendingByTag implements Serializable {
    private Integer order;
    private String tag;
    private Double amount;

    public SpendingByTag() {
    }

    public SpendingByTag(Integer order, String tag, Double amount) {
        this.order = order;
        this.tag = tag;
        this.amount = amount;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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
}
