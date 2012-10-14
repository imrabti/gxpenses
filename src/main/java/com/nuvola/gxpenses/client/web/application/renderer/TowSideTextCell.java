package com.nuvola.gxpenses.client.web.application.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;

import java.util.List;

public class TowSideTextCell extends AbstractCell<List<String>> {

    public interface Template extends SafeHtmlTemplates {
        @Template("<div><div style=\"float:left;\">{0}</div>" +
                "<div style=\"float:right;\">{1}</div>" +
                "<div style=\"clear:both;\"></div></div>")
        SafeHtml towSideTemplate(SafeHtml sideLeft, SafeHtml sideRight);
    }

    private final Template template;

    @Inject
    public TowSideTextCell(final Template template) {
        super("click");
        this.template = template;
    }

    @Override
    public void render(Context context, List<String> value, SafeHtmlBuilder sb) {
        SafeHtml safeLeft = SafeHtmlUtils.fromString(value.get(0));
        SafeHtml safeRight = SafeHtmlUtils.fromString(value.get(1));
        sb.append(template.towSideTemplate(safeLeft, safeRight));
    }

}

