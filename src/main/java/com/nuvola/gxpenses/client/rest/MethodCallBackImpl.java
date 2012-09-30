package com.nuvola.gxpenses.client.rest;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MethodCallBackImpl<T> implements MethodCallback<T>, HasHandlers {

    private static final Logger logger = Logger.getLogger(MethodCallBackImpl.class.getName());

    @Inject
    protected static EventBus eventBus;

    @Override
    public void onFailure(Method method, Throwable throwable) {
        logger.log(Level.SEVERE, throwable.getMessage());
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

}
