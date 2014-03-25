package com.nuvola.gxpenses.client.web.application.report.renderer;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.gin.ChartColor;
import com.nuvola.gxpenses.client.gin.Currency;
import com.nuvola.gxpenses.common.shared.dto.SpendingByTag;

public class TopSpendingCell extends AbstractCell<SpendingByTag> {
    public interface Renderer extends UiRenderer {
        void render(SafeHtmlBuilder sb, String tag, String color, String amount);
    }

    private final Renderer uiRenderer;
    private final NumberFormat numberFormat;
    private final List<String> chartColors;

    @Inject
    public TopSpendingCell(final Renderer uiRenderer, @Currency String currency,
                           @ChartColor List chartColors) {
        this.uiRenderer = uiRenderer;
        this.numberFormat = NumberFormat.getCurrencyFormat(currency);
        this.chartColors = chartColors;
    }

    @Override
    public void render(Context context, SpendingByTag value, SafeHtmlBuilder safeHtmlBuilder) {
        String color = "background-color: " + chartColors.get(value.getOrder());
        String amount = numberFormat.format(value.getAmount());
        uiRenderer.render(safeHtmlBuilder, value.getTag(), color, amount);
    }
}
