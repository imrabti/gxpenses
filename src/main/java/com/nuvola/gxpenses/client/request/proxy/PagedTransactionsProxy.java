package com.nuvola.gxpenses.client.request.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nuvola.gxpenses.server.dto.PagedTransactions;

import java.util.List;

@ProxyFor(PagedTransactions.class)
public interface PagedTransactionsProxy {
    List<TransactionProxy> getTransactions();

    void setTransactions(List<TransactionProxy> transactions);

    Integer getTotalElements();

    void setTotalElements(Integer totalElements);
}
