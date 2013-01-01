package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class SpendingByTagPresenter extends PresenterWidget<SpendingByTagPresenter.MyView>
        implements SpendingByTagUiHandlers  {
    public interface MyView extends View, HasUiHandlers<SpendingByTagUiHandlers> {
    }

    @Inject
    public SpendingByTagPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }
}
