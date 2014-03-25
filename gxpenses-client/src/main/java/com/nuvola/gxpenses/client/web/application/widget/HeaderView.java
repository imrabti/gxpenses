package com.nuvola.gxpenses.client.web.application.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ValuePicker;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.place.PlaceType;
import com.nuvola.gxpenses.client.resource.style.list.NavigationListStyle;
import com.nuvola.gxpenses.client.web.application.renderer.EnumCell;

public class HeaderView extends ViewWithUiHandlers<HeaderUiHandlers> implements HeaderPresenter.MyView {
    public interface Binder extends UiBinder<Widget, HeaderView> {
    }

    @UiField(provided = true)
    ValuePicker<PlaceType> placesBox;
    @UiField
    Label username;

    @Inject
    HeaderView(Binder uiBinder,
               NavigationListStyle listResources) {
        CellList<PlaceType> placeList = new CellList<PlaceType>(new EnumCell<PlaceType>(), listResources);
        placesBox = new ValuePicker<PlaceType>(placeList);

        placesBox.setAcceptableValues(PlaceType.getMainMenu());
        placesBox.setValue(PlaceType.TRANSACTIONS);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setUserName(String username) {
        this.username.setText(username);
    }

    @Override
    public void setSelectedMenu(PlaceType selectedMenu) {
        if (selectedMenu == PlaceType.SETTINGS) {
            placesBox.setValue(PlaceType.NONE);
        } else {
            placesBox.setValue(selectedMenu);
        }
    }

    @UiHandler("settings")
    void onSettingsClicked(ClickEvent event) {
        placesBox.setValue(PlaceType.NONE);
        getUiHandlers().changePlace(PlaceType.SETTINGS);
    }

    @UiHandler("placesBox")
    void onPlaceChanged(ValueChangeEvent<PlaceType> event) {
        getUiHandlers().changePlace(event.getValue());
    }

    @UiHandler("logout")
    void onLogoutClicked(ClickEvent event) {
        getUiHandlers().clearCredential();
    }
}
