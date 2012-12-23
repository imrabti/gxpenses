package com.nuvola.gxpenses.server.dto;

import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

public class TransactionFilter {
    private Long accountId;
    private PeriodType periodFilter;
    private TransactionType type;

    public TransactionFilter(Long accountId, PeriodType periodFilter, TransactionType type) {
        this.accountId = accountId;
        this.periodFilter = periodFilter;
        this.type = type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public PeriodType getPeriodFilter() {
        return periodFilter;
    }

    public void setPeriodFilter(PeriodType periodFilter) {
        this.periodFilter = periodFilter;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
