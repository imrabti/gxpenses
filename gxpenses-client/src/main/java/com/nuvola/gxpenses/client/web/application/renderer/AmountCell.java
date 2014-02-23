package com.nuvola.gxpenses.client.web.application.renderer;


import com.google.common.base.Objects;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.gin.Currency;
import com.nuvola.gxpenses.client.resource.Resources;

public class AmountCell extends AbstractCell<Double> {

    public interface Template extends SafeHtmlTemplates {
        @Template("<span class=\"{1}\">{0}</span>")
        SafeHtml amountTemplate(SafeHtml amount, String cssClass);
    }

    private final Template template;
    private final Resources resources;
    private final String currency;

    @Inject
    public AmountCell(final Template template, final Resources resources,
                      @Currency String currency) {
        this.template = template;
        this.resources = resources;
        this.currency = currency;
    }

    @Override
    public void render(Context context, Double value, SafeHtmlBuilder sb) {
        NumberFormat format = NumberFormat.getFormat("###,##0.00");
        SafeHtml safeAmount = SafeHtmlUtils.fromString(currency + format.format(Objects.firstNonNull(value, 0d)));
        String style = Objects.firstNonNull(value, 0d) > 0d ? resources.generalStyleCss().amountIncome() :
                resources.generalStyleCss().amountExpense();
        sb.append(template.amountTemplate(safeAmount, style));
    }

}

