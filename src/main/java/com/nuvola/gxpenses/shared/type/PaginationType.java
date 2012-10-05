package com.nuvola.gxpenses.shared.type;

public enum PaginationType {

    PAGE_40("40 by page", 40),
    PAGE_60("60 by page", 60),
    PAGE_80("80 by page", 80),
    PAGE_100("100 by page", 100);

    private final String label;
    private final Integer value;

    private PaginationType(String label, Integer value) {
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
