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
import com.nuvola.gxpenses.server.business.Budget;

public class BudgetCell extends AbstractCell<Budget> {

    public interface Template extends SafeHtmlTemplates {
        @Template("<div style=\"float: left;\"><div><div style=\"float:left; padding: 3px; margin: 4px;\">{0}</div>" +
                  "<div style=\"float:right; margin: 4px;\" class=\"elementBalance\">{1}</div>" +
                  "<div style=\"clear:both;\"></div></div>" +
                  "<div style=\"padding: 4px; padding-top: 2px;\">" +
                  "<div class=\"progress\"><div style=\"{2}\"></div></div></div></div>" +
                  "<div style=\"clear:both;\"></div></div>")
        SafeHtml budgetTemplate(SafeHtml name, SafeHtml allowed, SafeStyles percentage);
    }

    private final Template template;
    private final String currency;

    @Inject
    public BudgetCell(final Template template, @Currency String currency) {
        super("click");

        this.template = template;
        this.currency = currency;
    }

    @Override
    public void render(Context context, Budget value, SafeHtmlBuilder sb) {
        NumberFormat format = NumberFormat.getFormat("###,##0.00");

        String purcentage = value.getPercentageConsumed() > 100 ? "100" : value.getPercentageConsumed().toString();
        SafeHtml safeAllowed = SafeHtmlUtils.fromString(format.format(value.getTotalAllowed()) + " " + currency);
        SafeHtml safeName = SafeHtmlUtils.fromString(value.getName());
        SafeStyles safePurcentage = SafeStylesUtils.fromTrustedString("width: " + purcentage + "%;");

        sb.append(template.budgetTemplate(safeName, safeAllowed, safePurcentage));
    }

}
