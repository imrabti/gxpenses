package com.nuvola.gxpenses.client.request.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.nuvola.gxpenses.server.dto.TransferTransaction;

@ProxyFor(TransferTransaction.class)
public interface TransferTransactionProxy extends ValueProxy {
    public AccountProxy getSourceAccount();

    public void setSourceAccount(AccountProxy sourceAccount);

    public AccountProxy getTargetAccount();

    public void setTargetAccount(AccountProxy targetAccount);

    public Double getAmount();

    public void setAmount(Double amount);
}
