package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created with IntelliJ IDEA.
 * User: imrabti
 * Date: 10/16/12
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddBudgetElementView {
    interface AddBudgetElementViewUiBinder extends UiBinder<HTMLPanel, AddBudgetElementView> {
    }

    private static AddBudgetElementViewUiBinder ourUiBinder = GWT.create(AddBudgetElementViewUiBinder.class);

    public AddBudgetElementView() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    }
}
