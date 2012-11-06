package com.nuvola.gxpenses.client.rest;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.nuvola.gxpenses.client.event.HideLoadingEvent;
import com.nuvola.gxpenses.client.event.ShowLoadingEvent;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MethodCallbackImpl<T> implements MethodCallback<T>, HasHandlers {

    private static final Logger logger = Logger.getLogger(MethodCallbackImpl.class.getName());

    @Inject
    protected static EventBus eventBus;

    public MethodCallbackImpl() {
        ShowLoadingEvent.fire(this);
    }

    @Override
    public void onSuccess(Method method, T response) {
        HideLoadingEvent.fire(this);

        handleSuccess(response);
    }

    @Override
    public void onFailure(Method method, Throwable throwable) {
        HideLoadingEvent.fire(this);

        logger.log(Level.SEVERE, throwable.getMessage());
    }

    public abstract void handleSuccess(T result);

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

}
