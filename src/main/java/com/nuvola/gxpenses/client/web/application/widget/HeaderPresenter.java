package com.nuvola.gxpenses.client.web.application.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.nuvola.gxpenses.client.BootStrapper;
import com.nuvola.gxpenses.client.place.PlaceType;

public class HeaderPresenter extends PresenterWidget<HeaderPresenter.MyView> implements HeaderUiHandlers {

    public interface MyView extends View, HasUiHandlers<HeaderUiHandlers> {
        public void setUserName(String username);
    }

    private final PlaceManager placeManager;
    private final BootStrapper bootStrapper;

    @Inject
    public HeaderPresenter(EventBus eventBus, MyView view, final PlaceManager placeManager,
                           final BootStrapper bootStrapper) {
        super(eventBus, view);

        this.placeManager = placeManager;
        this.bootStrapper = bootStrapper;

        getView().setUiHandlers(this);
    }

    @Override
    public void changePlace(PlaceType newPlace) {
        PlaceRequest place = new PlaceRequest(newPlace.toString());
        placeManager.revealPlace(place);
    }

    @Override
    public void clearCredential() {
        bootStrapper.logout();
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        getView().setUserName(bootStrapper.getCurrentUser().getUserName());
    }

}
