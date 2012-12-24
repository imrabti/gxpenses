package com.nuvola.gxpenses.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.nuvola.gxpenses.client.gin.ClientGinjector;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Gxpenses implements EntryPoint {

    private static final Logger log = Logger.getLogger(Gxpenses.class.getName());
    private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

    @Override
    public void onModuleLoad() {
        DelayedBindRegistry.bind(ginjector);

        ginjector.getResources().generalStyleCss().ensureInjected();
        ginjector.getResources().popupStyleCss().ensureInjected();
        ginjector.getResources().buttonStyleCss().ensureInjected();
        ginjector.getResources().suggestBoxStyleCss().ensureInjected();
        ginjector.getResources().datePickerStyle().ensureInjected();
        ginjector.getResources().progressBarStyle().ensureInjected();

        ginjector.getBootStrapper().init();

        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            public void onUncaughtException(Throwable e) {
                log.log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }
}
