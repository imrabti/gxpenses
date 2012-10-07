package com.nuvola.gxpenses.shared.type;

public enum ImportFileType {

    QIF("Qif", "QIF"),
    GNU("Gnu", "GNU");

    private final String label;
    private final String value;

    private ImportFileType(String label, String value) {
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
