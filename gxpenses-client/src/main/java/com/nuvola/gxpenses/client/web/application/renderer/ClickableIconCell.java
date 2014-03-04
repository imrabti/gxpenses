package com.nuvola.gxpenses.client.web.application.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class ClickableIconCell<T> extends AbstractCell<T> {

    public interface Template extends SafeHtmlTemplates {
        @Template("<div style=\"padding: 2px;\">{0}</div>")
        SafeHtml clickableImage(SafeHtml image);
    }

    private final Template template;
    private final ActionCell.Delegate<T> delegate;
    private final ImageResource res;

    public ClickableIconCell(final Template template, ImageResource res, ActionCell.Delegate<T> delegate) {
        super("click");

        this.template = template;
        this.delegate = delegate;
        this.res = res;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, T value, NativeEvent event,
                               ValueUpdater<T> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if ("click".equals(event.getType())) {
            EventTarget eventTarget = event.getEventTarget();
            if (!Element.is(eventTarget)) {
                return;
            }
            if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
                delegate.execute(value);
            }
        }
    }

    @Override
    public void render(Context context, T value, SafeHtmlBuilder sb) {
        AbstractImagePrototype proto = AbstractImagePrototype.create(res);
        SafeHtml image = SafeHtmlUtils.fromTrustedString(proto.getHTML());
        sb.append(template.clickableImage(image));
    }

}
