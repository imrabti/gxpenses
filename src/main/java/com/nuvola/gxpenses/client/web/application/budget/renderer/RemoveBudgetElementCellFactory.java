package com.nuvola.gxpenses.client.web.application.budget.renderer;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.resources.client.ImageResource;
import com.nuvola.gxpenses.server.business.BudgetElement;

public interface RemoveBudgetElementCellFactory {
    RemoveBudgetElementCell create(ImageResource image, ActionCell.Delegate<BudgetElement> delegate);
}
