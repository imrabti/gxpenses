package com.nuvola.gxpenses.client.web.application.report.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.shared.dto.SpendingByTag;

import java.util.ArrayList;
import java.util.List;

public class SpendingByTagPresenter extends PresenterWidget<SpendingByTagPresenter.MyView>
        implements SpendingByTagUiHandlers  {
    public interface MyView extends View, HasUiHandlers<SpendingByTagUiHandlers> {
        void drawChart(List<SpendingByTag> data);
    }

    @Inject
    public SpendingByTagPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }

    protected void onReveal() {
        List<SpendingByTag> data = new ArrayList<SpendingByTag>();
        data.add(new SpendingByTag(0, "home renting", 450d));
        data.add(new SpendingByTag(1, "food", 210d));
        data.add(new SpendingByTag(2, "entertainment", 135.6d));
        data.add(new SpendingByTag(3, "well being", 89d));
        data.add(new SpendingByTag(4, "bills", 60d));
        data.add(new SpendingByTag(5, "other...", 21d));

        getView().drawChart(data);
    }
}
