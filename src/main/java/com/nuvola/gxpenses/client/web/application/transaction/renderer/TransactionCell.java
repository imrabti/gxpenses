package com.nuvola.gxpenses.client.web.application.transaction.renderer;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nuvola.gxpenses.client.gin.Currency;
import com.nuvola.gxpenses.client.resource.GxpensesRes;
import com.nuvola.gxpenses.shared.domaine.Transaction;

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
import com.nuvola.gxpenses.shared.type.TransactionType;

public class TransactionCell extends AbstractCell<Transaction> {
	
	public interface Template extends SafeHtmlTemplates {
		@Template("<span class=\"removeButton\"></span><div style=\"float:left;\" class=\"{4}\">{0}</div>" +
				"<div style=\"float:left;\"><p class=\"{5}\">{1}</p><p>{2}</p></div>" +
				"<div style=\"float:right;\" class=\"{6}\">{3}</div>" +
				"<div style=\"clear:both;\"></div>")
		SafeHtml transactionTemplate(SafeHtml date, SafeHtml payee, SafeHtml tags, SafeHtml amount,
                                     String dateCss, String payeeCss, String amountCss);
		
		@Template("<span class=\"removeButton\"></span><div style=\"float:left;\" class=\"{3}\">{0}</div>" +
				"<div style=\"float:left;\"><p class=\"{4}\" style=\"padding-top: 10px;\">{1}</p></div>" +
				"<div style=\"float:right;\" class=\"{5}\">{2}</div>" +
				"<div style=\"clear:both;\"></div>")
		SafeHtml transactionTemplateWithoutTags(SafeHtml date, SafeHtml payee, SafeHtml amount,
                                                String dateCss, String payeeCss, String amountCss);
		
		@Template("<span class=\"{1}\"><span>{0}</span></span>")
		SafeHtml tagTemplate(SafeHtml name, String tagCssClass);
	}
	
	private final Template template;
    private final GxpensesRes resources;
	private final Delegate<Transaction> delegate;
    private final NumberFormat numberFormat;
    private final DateTimeFormat dateFormat;

	private Transaction selectedTransaction;

    @Inject
	public TransactionCell(final Template template, final GxpensesRes resources,
                           @Currency String currency,
                           @Assisted Delegate<Transaction> delegate) {
		super("click");

        this.template = template;
        this.resources = resources;
        this.delegate = delegate;

        numberFormat = NumberFormat.getCurrencyFormat(currency);
        dateFormat = DateTimeFormat.getFormat("LLL d yyyy");
	}

	@Override
	public void onBrowserEvent(Context context, Element parent, Transaction value, NativeEvent event,
                               ValueUpdater<Transaction> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		if ("click".equals(event.getType())) {
			selectedTransaction = value;
			EventTarget eventTarget = event.getEventTarget();
			if (!Element.is(eventTarget)) {
				return;
			}
		
			if(Element.as(eventTarget).getClassName().equals("removeButton")) {
				delegate.execute(value);
			}
		}
	}

	@Override
	public void render(Context context, Transaction value, SafeHtmlBuilder sb) {
        if (value != null) {
            SafeHtml safeDate = SafeHtmlUtils.fromString(dateFormat.format(value.getDate()));
            SafeHtml safePayee = SafeHtmlUtils.fromString(value.getPayee());
            SafeHtml safeAmount = getSafeAmount(value.getAmount(), value.getType());

            String dateStyle = resources.generalStyleCss().date();
            String payeeStyle = resources.generalStyleCss().payee();
            String tagStyle = resources.generalStyleCss().tag();
            String amountStyle = getAmountStyle(value.getType());

            if(value == selectedTransaction) {
                dateStyle = resources.generalStyleCss().dateWhite();
                payeeStyle = resources.generalStyleCss().payeeWhite();
                amountStyle = resources.generalStyleCss().amountWhite();
                tagStyle = resources.generalStyleCss().tagWhite();
            }

            SafeHtmlBuilder tagsBuilder = new SafeHtmlBuilder();
            if(value.getTags() != null) {
                renderTags(value.getTags(), tagStyle, tagsBuilder);
            }

            if(value.getTags() != null) {
                sb.append(template.transactionTemplate(safeDate, safePayee, tagsBuilder.toSafeHtml(), safeAmount,
                        dateStyle, payeeStyle, amountStyle));
            } else {
                sb.append(template.transactionTemplateWithoutTags(safeDate, safePayee, safeAmount, dateStyle,
                        payeeStyle, amountStyle));
            }
        }
	}

    private SafeHtml getSafeAmount(Double amount, TransactionType type) {
        String balance;
        if (type == TransactionType.EXPENSE) {
            balance = numberFormat.format(amount);
        } else {
            balance = numberFormat.format(-amount);
        }

        return SafeHtmlUtils.fromString(balance);
    }

    private String getAmountStyle(TransactionType type) {
        if (type == TransactionType.INCOME) {
            return resources.generalStyleCss().amountIncomeTrans();
        } else {
            return resources.generalStyleCss().amountExpenseTrans();
        }
    }

    private void renderTags(String value, String tagCss, SafeHtmlBuilder sb) {
        SafeHtml safeTag;
        List<String> tags = Arrays.asList(value.split(","));

        for(String tag : tags) {
            safeTag = SafeHtmlUtils.fromString(tag);
            sb.append(template.tagTemplate(safeTag, tagCss));
        }
    }

}
