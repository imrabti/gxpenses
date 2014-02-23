package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class SpendingOverTimePresenter extends PresenterWidget<SpendingOverTimePresenter.MyView>
        implements SpendingOverTimeUiHandlers {
    public interface MyView extends View, HasUiHandlers<SpendingOverTimeUiHandlers> {
    }

    @Inject
    public SpendingOverTimePresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }
}
