package com.nuvola.gxpenses.client.web.application.budget.renderer;

import com.google.common.base.Objects;
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
import com.nuvola.gxpenses.server.business.BudgetElement;

public class BudgetProgressCell extends AbstractCell<BudgetElement> {

    public interface Template extends SafeHtmlTemplates {
        @Template("<div class=\"{2}\"><span style=\"{1}\">" +
                "<div style=\"margin-right: 6px;\">{0}</div></span></div>")
        SafeHtml progressTemplate(SafeHtml consumed, SafeStyles purcentage, String cssClass);

        @Template("<div class=\"{1}\"><div>{0}</div></div>")
        SafeHtml progressTemplateBlank(SafeHtml consumed, String cssClass);
    }

    private final Template template;
    private final Resources resources;
    private final String currency;

    @Inject
    public BudgetProgressCell(final Template template, final Resources resources,
                              @Currency String currency) {
        this.template = template;
        this.resources = resources;
        this.currency = currency;
    }

    @Override
    public void render(Context context, BudgetElement value, SafeHtmlBuilder sb) {
        NumberFormat format = NumberFormat.getFormat("###,##0.00");
        Integer percentage = (int)((Objects.firstNonNull(value.getConsumedAmount(), 0d) * 100) / value.getAmount());

        SafeHtml safeConsumed = SafeHtmlUtils.fromString(percentage > 20 ? currency +
                format.format(Objects.firstNonNull(value.getConsumedAmount(), 0d)) : "");
        String style = resources.progressBarStyle().bigProgress() + " ";

        if(percentage > 0) {
            if(percentage > 100) {
                style += resources.progressBarStyle().red();
            } else if(percentage > 70 && percentage < 100) {
                style += resources.progressBarStyle().orange();
            } else {
                style += resources.progressBarStyle().green();
            }

            percentage = percentage > 100 ? 100 : percentage;
            SafeStyles safePurcentage = SafeStylesUtils.fromTrustedString("width: " + percentage +"%;");
            sb.append(template.progressTemplate(safeConsumed, safePurcentage, style));
        } else {
            safeConsumed = SafeHtmlUtils.fromString(currency + format.format(0d));
            sb.append(template.progressTemplateBlank(safeConsumed, style));
        }
    }

}
