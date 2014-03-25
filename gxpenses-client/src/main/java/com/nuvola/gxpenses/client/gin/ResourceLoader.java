package com.nuvola.gxpenses.client.gin;

import javax.inject.Inject;

import com.nuvola.gxpenses.client.resource.Resources;

public class ResourceLoader {
    @Inject
    ResourceLoader(Resources resources) {
        resources.generalStyleCss().ensureInjected();
        resources.popupStyleCss().ensureInjected();
        resources.buttonStyleCss().ensureInjected();
        resources.suggestBoxStyleCss().ensureInjected();
        resources.datePickerStyle().ensureInjected();
        resources.progressBarStyle().ensureInjected();
    }
}
