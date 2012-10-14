package com.nuvola.gxpenses.client.web.application.budget;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.UiHandlers;

public interface BudgetUiHandlers extends UiHandlers {
    void elementSetting(Widget relativeTo);

    void nextPeriod();

    void previousPeriod();
}
