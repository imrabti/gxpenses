package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nuvola.gxpenses.client.gin.Currency;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.shared.domaine.Account;

public class AccountCell extends AbstractCell<Account> {

    public interface Template extends SafeHtmlTemplates {
        @Template("<span class=\"removeButton\"></span><div style=\"float:left; padding: 3px; margin: 4px;\">{0}</div>" +
                "<div style=\"float:right; margin: 4px;\" class=\"{2}\">{1}</div>" +
                "<div style=\"clear:both;\"></div>")
        SafeHtml accountTemplate(SafeHtml name, SafeHtml balance, String cssClass);
    }

    private final Template template;
    private final Resources resources;
    private final Delegate<Account> delegate;
    private final String currency;

    private Account selectedAccount;

    @Inject
    public AccountCell(final Template template, final Resources resources,
                       @Currency String currency,
                       @Assisted Delegate<Account> delegate) {
        super("click");

        this.template = template;
        this.resources = resources;
        this.currency = currency;
        this.delegate = delegate;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, Account value, NativeEvent event,
                               ValueUpdater<Account> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if ("click".equals(event.getType())) {
            selectedAccount = value;
            EventTarget eventTarget = event.getEventTarget();
            if (!Element.is(eventTarget)) {
                return;
            }

            if (Element.as(eventTarget).getClassName().equals("removeButton")) {
                delegate.execute(value);
            }
        }
    }

    @Override
    public void render(Context context, Account value, SafeHtmlBuilder sb) {
        NumberFormat format = NumberFormat.getFormat("###,##0.00");
        String balance = (null == value.getBalance()) ? format.format(0d) : format.format(value.getBalance());

        SafeHtml safeName = SafeHtmlUtils.fromString(value.getName());
        SafeHtml safeBalance = SafeHtmlUtils.fromString(balance + " " + currency);
        String balanceStyle;

        if (value == selectedAccount) {
            balanceStyle = resources.generalStyleCss().accountBalanceSelected();
        } else {
            balanceStyle = resources.generalStyleCss().accountBalance();
        }

        sb.append(template.accountTemplate(safeName, safeBalance, balanceStyle));
    }

}
