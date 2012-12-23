package com.nuvola.gxpenses.client.request.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.nuvola.gxpenses.server.dto.TransactionFilter;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

@ProxyFor(TransactionFilter.class)
public interface TransactionFilterProxy extends ValueProxy {
    Long getAccountId();

    void setAccountId(Long accountId);

    PeriodType getPeriodFilter();

    void setPeriodFilter(PeriodType periodFilter);

    TransactionType getType();

    void setType(TransactionType type);
}
