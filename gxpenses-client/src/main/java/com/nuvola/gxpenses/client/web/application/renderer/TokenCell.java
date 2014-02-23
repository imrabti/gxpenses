package com.nuvola.gxpenses.client.web.application.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nuvola.gxpenses.client.resource.Resources;

public class TokenCell extends AbstractCell<String> {
    public interface Template extends SafeHtmlTemplates {
        @Template("<div style=\"float:left;\">{0}</div><span class=\"removeToken\"></span>" +
                  "<div style=\"clear:both;\"></div>")
        SafeHtml tagTemplate(SafeHtml name);
    }

    private final Template template;
    private final Delegate<String> delegate;

    @Inject
    public TokenCell(final Template template, final Resources resources,
                     @Assisted Delegate<String> delegate) {
        super("click");

        this.template = template;
        this.delegate = delegate;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
                               ValueUpdater<String> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if ("click".equals(event.getType())) {
            EventTarget eventTarget = event.getEventTarget();
            if (!Element.is(eventTarget)) {
                return;
            }

            if (Element.as(eventTarget).getClassName().equals("removeToken")) {
                delegate.execute(value);
            }
        }
    }

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        sb.append(template.tagTemplate(SafeHtmlUtils.fromString(value)));
    }
}
