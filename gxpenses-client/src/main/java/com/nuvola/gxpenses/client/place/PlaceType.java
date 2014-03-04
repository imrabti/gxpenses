package com.nuvola.gxpenses.client.place;

import java.util.ArrayList;
import java.util.List;

public enum PlaceType {
    TRANSACTIONS("Transactions"),
    BUDGETS("Budgets"),
    REPORTS("Reports"),
    SETTINGS("Settings"),
    NONE("None");

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

    public static PlaceType getPlaceByName(String name) {
        if (name.equals(TRANSACTIONS.getLabel())) {
            return TRANSACTIONS;
        } else if (name.equals(BUDGETS.getLabel())) {
            return BUDGETS;
        } else if (name.equals(REPORTS.getLabel())) {
            return REPORTS;
        } else if (name.equals(SETTINGS.getLabel())) {
            return SETTINGS;
        } else {
            return NONE;
        }
    }

    @Override
    public String toString() {
        return label;
    }
}
