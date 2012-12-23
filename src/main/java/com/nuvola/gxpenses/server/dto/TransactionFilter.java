package com.nuvola.gxpenses.server.dto;

import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

public class TransactionFilter {
    private Long accountId;
    private PeriodType period;
    private TransactionType type;

    public TransactionFilter(Long accountId, PeriodType period, TransactionType type) {
        this.accountId = accountId;
        this.period = period;
        this.type = type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public PeriodType getPeriod() {
        return period;
    }

    public void setPeriod(PeriodType period) {
        this.period = period;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
