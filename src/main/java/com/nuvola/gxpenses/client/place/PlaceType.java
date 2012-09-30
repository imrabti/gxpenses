package com.nuvola.gxpenses.client.place;

import java.util.ArrayList;
import java.util.List;

public enum PlaceType {

    TRANSACTIONS("Transactions"),
    BUDGETS("Budgets"),
    REPORTS("Reports"),
    SETTINGS("Settings");

    private String label;

    private PlaceType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<PlaceType> getMainMenu() {
        List<PlaceType> menu = new ArrayList<PlaceType>();
        menu.add(TRANSACTIONS);
        menu.add(BUDGETS);
        menu.add(REPORTS);

        return menu;
    }

    @Override
    public String toString() {
        return label;
    }

}
