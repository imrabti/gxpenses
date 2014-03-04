package com.nuvola.gxpenses.client.web.application.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.nuvola.gxpenses.client.gin.CurrentUser;
import com.nuvola.gxpenses.client.place.PlaceType;

public class HeaderPresenter extends PresenterWidget<HeaderPresenter.MyView> implements HeaderUiHandlers {
    public interface MyView extends View, HasUiHandlers<HeaderUiHandlers> {
        void setUserName(String username);

        void setSelectedMenu(PlaceType selectedMenu);
    }

    private final PlaceManager placeManager;
    private final CurrentUser currentUser;

    @Inject
    HeaderPresenter(EventBus eventBus,
                    MyView view,
                    PlaceManager placeManager,
                    CurrentUser currentUser) {
        super(eventBus, view);

        this.placeManager = placeManager;
        this.currentUser = currentUser;

        getView().setUiHandlers(this);
    }

    @Override
    public void changePlace(PlaceType newPlace) {
        PlaceRequest place = new PlaceRequest.Builder().nameToken(newPlace.toString()).build();
        placeManager.revealPlace(place);
    }

    @Override
    public void clearCredential() {
        // TODO : Logout code in here...
    }

    @Override
    protected void onReveal() {
        getView().setSelectedMenu(PlaceType.getPlaceByName(placeManager.getCurrentPlaceRequest().getNameToken()));
        getView().setUserName(currentUser.getUser().getUserName());
    }
}
