package com.nuvola.gxpenses.client.request.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.nuvola.gxpenses.server.business.BudgetElement;

@ProxyFor(BudgetElement.class)
public interface BudgetElementProxy extends ValueProxy {
    Long getId();

    void setId(Long id);

    String getTag();

    void setTag(String tag);

    Double getAmount();

    void setAmount(Double amount);

    Double getConsumedAmount();

    void setConsumedAmount(Double consumedAmount);

    Double getLeftAmount();

    void setLeftAmount(Double leftAmount);

    BudgetProxy getBudget();

    void setBudget(BudgetProxy budget);
}
