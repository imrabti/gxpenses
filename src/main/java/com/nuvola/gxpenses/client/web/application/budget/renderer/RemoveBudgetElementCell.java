package com.nuvola.gxpenses.client.web.application.budget.renderer;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nuvola.gxpenses.client.request.proxy.BudgetElementProxy;
import com.nuvola.gxpenses.client.web.application.renderer.ClickableIconCell;

public class RemoveBudgetElementCell extends ClickableIconCell<BudgetElementProxy> {
    @Inject
    public RemoveBudgetElementCell(final Template template, @Assisted ImageResource res,
                                   @Assisted ActionCell.Delegate<BudgetElementProxy> delegate) {
        super(template, res, delegate);
    }
}
