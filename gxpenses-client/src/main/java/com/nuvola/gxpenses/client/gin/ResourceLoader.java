package com.nuvola.gxpenses.client.gin;

import com.nuvola.gxpenses.client.resource.Resources;

import javax.inject.Inject;

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
