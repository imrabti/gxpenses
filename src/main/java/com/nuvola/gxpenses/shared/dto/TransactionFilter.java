package com.nuvola.gxpenses.shared.dto;

import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransactionFilter extends PageRequest {

    private Long accountId;
    private TransactionType typeFilter;
    private PeriodType periodFilter;

    public TransactionFilter() {
    }

    public TransactionFilter(Long accountId, TransactionType typeFilter, PeriodType periodFilter) {
        this.accountId = accountId;
        this.typeFilter = typeFilter;
        this.periodFilter = periodFilter;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public TransactionType getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(TransactionType typeFilter) {
        this.typeFilter = typeFilter;
    }

    public PeriodType getPeriodFilter() {
        return periodFilter;
    }

    public void setPeriodFilter(PeriodType periodFilter) {
        this.periodFilter = periodFilter;
    }

}
