package com.nuvola.gxpenses.common.shared.type;

public enum TransactionType {

    ALL("All", "ALL"),
    INCOME("Income", "IN"),
    EXPENSE("Expense", "OUT");

    private final String label;
    private final String value;

    private TransactionType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return label;
    }

}
