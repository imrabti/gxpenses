package com.nuvola.gxpenses.common.shared.dto;

import com.nuvola.gxpenses.common.shared.business.Account;

import java.io.Serializable;

public class TransferTransaction implements Serializable {
    private Account sourceAccount;
    private Account targetAccount;
    private Double amount;

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
