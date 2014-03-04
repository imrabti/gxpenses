package com.nuvola.gxpenses.common.shared.type;

public enum PeriodType {

    THIS_MONTH("This month", 0),
    LAST_MONTH("Last month", 1),
    THIS_QUARTER("This quarter", 2),
    LAST_QUARTER("Last quarter", 3),
    THIS_SEMESTER("This semester", 4),
    LAST_SEMESTER("Last semester", 5),
    THIS_YEAR("This year", 6),
    LAST_YEAR("Last year", 7);

    private final String label;
    private final Integer value;

    private PeriodType(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return label;
    }

}
