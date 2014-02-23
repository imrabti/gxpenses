package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nuvola.gxpenses.client.gin.Currency;
import com.nuvola.gxpenses.client.request.proxy.TransactionProxy;

import java.util.Arrays;
import java.util.List;

public class TransactionCell extends AbstractCell<TransactionProxy> {

    public interface Template extends SafeHtmlTemplates {
        @Template("<span class=\"removeButton\"></span><div style=\"float:left;\" class=\"date\">{0}</div>" +
                  "<div style=\"float:left;\"><p class=\"payee\">{1}</p><p>{2}</p></div>" +
                  "<div style=\"float:right;\" class=\"amount\">{3}</div>" +
                  "<div style=\"clear:both;\"></div>")
        SafeHtml transactionTemplate(SafeHtml date, SafeHtml payee, SafeHtml tags, SafeHtml amount);

        @Template("<span class=\"removeButton\"></span><div style=\"float:left;\" class=\"date\">{0}</div>" +
                  "<div style=\"float:left;\"><p class=\"payee\" style=\"padding-top: 10px;\">{1}</p></div>" +
                  "<div style=\"float:right;\" class=\"amount\">{2}</div>" +
                  "<div style=\"clear:both;\"></div>")
        SafeHtml transactionTemplateWithoutTags(SafeHtml date, SafeHtml payee, SafeHtml amount);

        @Template("<span class=\"tag\"><span>{0}</span></span>")
        SafeHtml tagTemplate(SafeHtml name);
    }

    private final Template template;
    private final Delegate<TransactionProxy> delegate;
    private final NumberFormat numberFormat;
    private final DateTimeFormat dateFormat;

    @Inject
    public TransactionCell(final Template template, @Currency String currency,
                           @Assisted Delegate<TransactionProxy> delegate) {
        super("click");

        this.template = template;
        this.delegate = delegate;

        numberFormat = NumberFormat.getCurrencyFormat(currency);
        dateFormat = DateTimeFormat.getFormat("LLL d yyyy");
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, TransactionProxy value, NativeEvent event,
                               ValueUpdater<TransactionProxy> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if ("click".equals(event.getType())) {
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
    public void render(Context context, TransactionProxy value, SafeHtmlBuilder sb) {
        if (value != null) {
            SafeHtml safeDate = SafeHtmlUtils.fromString(dateFormat.format(value.getDate()));
            SafeHtml safePayee = SafeHtmlUtils.fromString(value.getPayee());
            SafeHtml safeAmount = getSafeAmount(value.getAmount());

            SafeHtmlBuilder tagsBuilder = new SafeHtmlBuilder();
            if (value.getTags() != null) {
                renderTags(value.getTags(), tagsBuilder);
            }

            if (value.getTags() != null) {
                sb.append(template.transactionTemplate(safeDate, safePayee, tagsBuilder.toSafeHtml(), safeAmount));
            } else {
                sb.append(template.transactionTemplateWithoutTags(safeDate, safePayee, safeAmount));
            }
        }
    }

    private SafeHtml getSafeAmount(Double amount) {
        String balance = numberFormat.format(amount);
        return SafeHtmlUtils.fromString(balance);
    }

    private void renderTags(String value, SafeHtmlBuilder sb) {
        SafeHtml safeTag;
        List<String> tags = Arrays.asList(value.split(","));

        for (String tag : tags) {
            safeTag = SafeHtmlUtils.fromString(tag);
            sb.append(template.tagTemplate(safeTag));
        }
    }
}
