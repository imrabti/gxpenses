package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class SpendingByTagView extends ViewWithUiHandlers<SpendingByTagUiHandlers>
        implements SpendingByTagPresenter.MyView {
    public interface Binder extends UiBinder<Widget, SpendingByTagView> {
    }

    @UiField
    HTMLPanel chartWrapper;

    @Inject
    public SpendingByTagView(final Binder uiBinder,
                             final UiHandlersStrategy<SpendingByTagUiHandlers> uiHandlers) {
        super(uiHandlers);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void drawChart() {
        Runnable onLoadCallback = new Runnable() {
            public void run() {
                PieChart pie = new PieChart(prepareData(), prepareOptions());
                chartWrapper.clear();
                chartWrapper.add(pie);
            }
        };
        VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
    }

    private Options prepareOptions() {
        Options options = Options.create();
        options.setWidth(300);
        options.setHeight(220);
        options.set3D(false);
        options.setBackgroundColor("none");
        options.setLegend(LegendPosition.NONE);
        options.setColors("#3366cc", "#990099", "#109618", "#ff9900", "#dc3912");
        return options;
    }

    private AbstractDataTable prepareData() {
        DataTable data = DataTable.create();
        data.addColumn(AbstractDataTable.ColumnType.STRING, "Task");
        data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Hours per Day");
        data.addRows(5);
        data.setValue(0, 0, "Work");
        data.setValue(0, 1, 14);
        data.setValue(1, 0, "Sleep");
        data.setValue(1, 1, 10);
        data.setValue(2, 0, "Eat");
        data.setValue(2, 1, 8);
        data.setValue(3, 0, "Play");
        data.setValue(3, 1, 9);
        data.setValue(4, 0, "Study");
        data.setValue(4, 1, 4);
        return data;
    }
}
