package com.nuvola.gxpenses.client.web.application.budget.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.gin.Currency;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.common.shared.dto.BudgetProgressTotal;

public class BudgetProgressFooterCell extends AbstractCell<BudgetProgressTotal> {
    public interface Template extends SafeHtmlTemplates {
        @Template("<div class=\"{2}\"><span style=\"{1}\">" +
                "<div style=\"margin-right: 6px;\">{0}</div></span></div>")
        SafeHtml progressTemplate(SafeHtml consumed, SafeStyles percentage, String cssClass);

        @Template("<div class=\"{1}\"><div>{0}</div></div>")
        SafeHtml progressTemplateBlank(SafeHtml consumed, String cssClass);
    }

    private final Template template;
    private final Resources resources;
    private final String currency;

    @Inject
    BudgetProgressFooterCell(Template template,
                             Resources resources,
                             @Currency String currency) {
        this.template = template;
        this.resources = resources;
        this.currency = currency;
    }

    @Override
    public void render(Context context, BudgetProgressTotal value, SafeHtmlBuilder sb) {
        NumberFormat format = NumberFormat.getFormat("###,##0.00");
        Integer purcentage = (int)((value.getTotalConsumed() * 100) / value.getTotalAllowed());

        SafeHtml safeConsumed = SafeHtmlUtils.fromString(purcentage > 20 ? currency +
                format.format(value.getTotalConsumed()) : "");
        String style = resources.progressBarStyle().bigProgressTotal();

        if(purcentage > 0) {
            purcentage = purcentage > 100 ? 100 : purcentage;
            SafeStyles safePercentage = SafeStylesUtils.fromTrustedString("width: " + purcentage +"%;");
            sb.append(template.progressTemplate(safeConsumed, safePercentage, style));
        } else {
            safeConsumed = SafeHtmlUtils.fromString(currency + format.format(0d));
            sb.append(template.progressTemplateBlank(safeConsumed, style));
        }
    }
}
