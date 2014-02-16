package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.web.application.setting.event.SettingsMenuChangedEvent;

public class SettingSiderPresenter extends PresenterWidget<SettingSiderPresenter.MyView>
        implements SettingSiderUiHandlers {
    public interface MyView extends View, HasUiHandlers<SettingSiderUiHandlers> {
        void setDefaultMenu();
    }

    @Inject
    public SettingSiderPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }

    @Override
    public void changeMenu(SettingSiderView.SettingsEnum menu) {
        SettingsMenuChangedEvent.fire(this, menu);
    }

    @Override
    protected void onReveal() {
        getView().setDefaultMenu();
    }
}
