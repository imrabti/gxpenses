package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.BrowserEvents;
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
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;

public class AccountCell extends AbstractCell<AccountProxy> {
    public interface Template extends SafeHtmlTemplates {
        @Template("<span class=\"removeButton\"></span><div style=\"float:left; padding: 3px; margin: 4px;\">{0}</div>" +
                  "<div style=\"float:right; margin: 4px;\" class=\"elementBalance\">{1}</div>" +
                  "<div style=\"clear:both;\"></div>")
        SafeHtml accountTemplate(SafeHtml name, SafeHtml balance);
    }

    private final Template template;
    private final Delegate<AccountProxy> delegate;
    private final String currency;

    @Inject
    public AccountCell(final Template template, @Currency String currency,
                       @Assisted Delegate<AccountProxy> delegate) {
        super(BrowserEvents.CLICK);

        this.template = template;
        this.currency = currency;
        this.delegate = delegate;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, AccountProxy value, NativeEvent event,
                               ValueUpdater<AccountProxy> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if (BrowserEvents.CLICK.equals(event.getType())) {
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
    public void render(Context context, AccountProxy value, SafeHtmlBuilder sb) {
        NumberFormat format = NumberFormat.getFormat("###,##0.00");
        String balance = (null == value.getBalance()) ? format.format(0d) : format.format(value.getBalance());

        SafeHtml safeName = SafeHtmlUtils.fromString(value.getName());
        SafeHtml safeBalance = SafeHtmlUtils.fromString(balance + " " + currency);

        sb.append(template.accountTemplate(safeName, safeBalance));
    }
}
