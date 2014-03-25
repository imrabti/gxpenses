package com.nuvola.gxpenses.client.gin;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ChartColorProvider implements Provider<List<String>> {
    private final static String COLORS = "#3366cc,#990099,#109618,#ff9900,#dc3912,#7fcdbb";

    @Inject
    public ChartColorProvider() {
    }

    @Override
    public List<String> get() {
        return Arrays.asList(COLORS.split(","));
    }
}
