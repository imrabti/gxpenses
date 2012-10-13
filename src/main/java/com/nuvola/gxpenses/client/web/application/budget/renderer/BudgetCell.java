package com.nuvola.gxpenses.client.web.application.budget.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
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
import com.nuvola.gxpenses.shared.domaine.Budget;

public class BudgetCell extends AbstractCell<Budget> {

    public interface Template extends SafeHtmlTemplates {
        @Template("<div style=\"float: left;\"><div><div style=\"float:left; padding: 3px; margin: 4px;\">{0}</div>" +
                "<div style=\"float:right; margin: 4px;\" class=\"{3}\">{1}</div>" +
                "<div style=\"clear:both;\"></div></div>" +
                "<div style=\"padding: 4px; padding-top: 2px;\">" +
                "<div class=\"{4}\"><div style=\"{2}\"></div></div></div></div>" +
                "<div style=\"clear:both;\"></div></div>")
        SafeHtml budgetTemplate(SafeHtml name, SafeHtml allowed, SafeStyles percentage, String cssClass,
                                String progressClass);
    }

    private final Template template;
    private final Resources resources;
    private final String currency;

    private Budget selectedBudget;

    @Inject
    public BudgetCell(final Template template, final Resources resources,
                      @Currency String currency) {
        super("click");

        this.template = template;
        this.resources = resources;
        this.currency = currency;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, Budget value, NativeEvent event,
                               ValueUpdater<Budget> valueUpdater) {
        if ("click".equals(event.getType())) {
            selectedBudget = value;
        }
    }

    @Override
    public void render(Context context, Budget value, SafeHtmlBuilder sb) {
        NumberFormat format = NumberFormat.getFormat("###,##0.00");

        String purcentage = value.getPercentageConsumed() > 100 ? "100" : value.getPercentageConsumed().toString();
        String allowedStyle = resources.generalStyleCss().budgetAllowed();
        String progressStyle = resources.progressBarStyle().smallProgress();

        SafeHtml safeAllowed = SafeHtmlUtils.fromString(format.format(value.getTotalAllowed()) + " " + currency);
        SafeHtml safeName = SafeHtmlUtils.fromString(value.getName());
        SafeStyles safePurcentage = SafeStylesUtils.fromTrustedString("width: " + purcentage + "%;");

        if(value == selectedBudget) {
            allowedStyle = resources.generalStyleCss().budgetAllowedSelected();
            progressStyle = resources.progressBarStyle().smallProgressSelected();
        }

        sb.append(template.budgetTemplate(safeName, safeAllowed, safePurcentage, allowedStyle, progressStyle));
    }

}
