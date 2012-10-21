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
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
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
    public HeaderView(final Binder uiBinder,
                      final UiHandlersStrategy<HeaderUiHandlers> uiHandlers,
                      final NavigationListStyle listResources) {
        super(uiHandlers);

        CellList<PlaceType> placeList = new CellList<PlaceType>(new EnumCell<PlaceType>(), listResources);
        placesBox = new ValuePicker<PlaceType>(placeList);

        placesBox.setAcceptableValues(PlaceType.getMainMenu());
        placesBox.setValue(PlaceType.TRANSACTIONS);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("settings")
    public void onSettingsClicked(ClickEvent event) {
        placesBox.setValue(PlaceType.NONE);
        getUiHandlers().changePlace(PlaceType.SETTINGS);
    }

    @UiHandler("placesBox")
    public void onPlaceChanged(ValueChangeEvent<PlaceType> event) {
        getUiHandlers().changePlace(event.getValue());
    }

    @UiHandler("logout")
    public void onLogoutClicked(ClickEvent event) {
        getUiHandlers().clearCredential();
    }

    @Override
    public void setUserName(String username) {
        this.username.setText(username);
    }

}
