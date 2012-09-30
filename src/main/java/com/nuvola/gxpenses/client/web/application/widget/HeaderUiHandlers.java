package com.nuvola.gxpenses.client.web.application.widget;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.place.PlaceType;

public interface HeaderUiHandlers extends UiHandlers {
    void changePlace(PlaceType newPlace);

    void clearCredential();
}
