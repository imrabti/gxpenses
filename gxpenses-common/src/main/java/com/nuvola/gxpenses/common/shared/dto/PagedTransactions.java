package com.nuvola.gxpenses.common.shared.dto;

import com.nuvola.gxpenses.common.shared.business.Transaction;

import java.util.List;

public class PagedTransactions {
    private List<Transaction> transactions;
    private Integer totalElements;

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
}
