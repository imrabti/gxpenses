package com.nuvola.gxpenses.client.web.application.setting.importfile;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;

public interface ImportRes extends ClientBundle {

    @Source("resources/sample.qif")
    ExternalTextResource qifSample();

}
