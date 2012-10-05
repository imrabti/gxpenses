package com.nuvola.gxpenses.client.util;

import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.UmbrellaException;

public class CustomUncaughtExceptionHandler implements UncaughtExceptionHandler {

    @Override
    public void onUncaughtException(Throwable e) {
        // Get rid of UmbrellaException
        Throwable exceptionToDisplay = getExceptionToDisplay(e);
        Window.alert(exceptionToDisplay.getMessage());
    }

    private static Throwable getExceptionToDisplay(Throwable throwable) {
        Throwable result = throwable;
        if (throwable instanceof UmbrellaException
                && ((UmbrellaException) throwable).getCauses().size() == 1) {
            result = ((UmbrellaException) throwable).getCauses().iterator()
                    .next();
        }
        return result;
    }

}
