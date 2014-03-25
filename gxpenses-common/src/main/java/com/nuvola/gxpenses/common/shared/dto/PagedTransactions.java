package com.nuvola.gxpenses.common.shared.dto;

import java.util.List;

import com.nuvola.gxpenses.common.shared.business.Transaction;

public class PagedTransactions {
    private List<Transaction> transactions;
    private Integer totalElements;
    private Double totalAmount;

    public PagedTransactions() {
    }

    public PagedTransactions(List<Transaction> transactions, Integer totalElements) {
        this.transactions = transactions;
        this.totalElements = totalElements;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
