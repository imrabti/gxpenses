package com.nuvola.gxpenses.shared.dto;

public class BudgetProgressTotal implements Dto {

    private Double totalAllowed;
    private Double totalConsumed;

    public BudgetProgressTotal() {
        totalAllowed = 0d;
        totalConsumed = 0d;
    }

    public BudgetProgressTotal(Double totalAllowed, Double totalConsumed) {
        this.totalAllowed = totalAllowed;
        this.totalConsumed = totalConsumed;
    }

    public Double getTotalConsumed() {
        return totalConsumed;
    }

    public void setTotalConsumed(Double totalConsumed) {
        this.totalConsumed = totalConsumed;
    }

    public Double getTotalAllowed() {
        return totalAllowed;
    }

    public void setTotalAllowed(Double totalAllowed) {
        this.totalAllowed = totalAllowed;
    }

    public Double getTotalLeft() {
        return (totalAllowed - totalConsumed);
    }

}
