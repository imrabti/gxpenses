package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.gin.ChartColor;
import com.nuvola.gxpenses.client.resource.style.list.TopSpendingListStyle;
import com.nuvola.gxpenses.client.web.application.report.renderer.TopSpendingCell;
import com.nuvola.gxpenses.shared.dto.SpendingByTag;

import java.util.List;

public class SpendingByTagView extends ViewWithUiHandlers<SpendingByTagUiHandlers>
        implements SpendingByTagPresenter.MyView {
    public interface Binder extends UiBinder<Widget, SpendingByTagView> {
    }

    private final static String TAG_COLUMN = "Tag";
    private final static String AMOUNT_COLUMN = "Total Amount";

    @UiField
    HTMLPanel chartWrapper;
    @UiField(provided = true)
    CellList<SpendingByTag> topSpendingList;

    private final List<String> chartColors;
    private final ListDataProvider<SpendingByTag> dataProvider;

    @Inject
    public SpendingByTagView(final Binder uiBinder,
                             final TopSpendingListStyle listStyle, final TopSpendingCell topSpendingCell,
                             @ChartColor List chartColors) {
        this.chartColors = chartColors;
        this.topSpendingList = new CellList<SpendingByTag>(topSpendingCell, listStyle);
        this.dataProvider = new ListDataProvider<SpendingByTag>();

        initWidget(uiBinder.createAndBindUi(this));
        dataProvider.addDataDisplay(topSpendingList);
    }

    @Override
    public void drawChart(final List<SpendingByTag> data) {
        Runnable onLoadCallback = new Runnable() {
            public void run() {
                PieChart pie = new PieChart(prepareData(data), prepareOptions());
                chartWrapper.clear();
                chartWrapper.add(pie);

                dataProvider.getList().clear();
                dataProvider.getList().addAll(data);
                dataProvider.refresh();
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
        options.setColors(
                chartColors.get(0),
                chartColors.get(1),
                chartColors.get(2),
                chartColors.get(3),
                chartColors.get(4),
                chartColors.get(5)
        );
        return options;
    }

    private AbstractDataTable prepareData(List<SpendingByTag> chartData) {
        DataTable data = DataTable.create();
        data.addColumn(AbstractDataTable.ColumnType.STRING, TAG_COLUMN);
        data.addColumn(AbstractDataTable.ColumnType.NUMBER, AMOUNT_COLUMN);
        data.addRows(chartData.size());

        for (int i = 0; i < chartData.size(); i++) {
            data.setValue(i, 0, chartData.get(i).getTag());
            data.setValue(i, 1, chartData.get(i).getAmount());
        }

        return data;
    }
}
