package com.nuvola.gxpenses.common.client.rest;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.nuvola.gxpenses.common.client.event.RequestEvent;

public abstract class AsyncCallbackImpl<T> implements AsyncCallback<T>, HasHandlers {
    @Inject
    protected static EventBus eventBus;

    public AsyncCallbackImpl() {
        RequestEvent.fire(this, RequestEvent.State.SENT, this);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

    @Override
    public void onSuccess(T response) {
        RequestEvent.fire(this, RequestEvent.State.RECEIVED, this);
    }

    @Override
    public void onFailure(Throwable exception) {
        RequestEvent.fire(this, RequestEvent.State.RECEIVED, this);

        Window.alert("ERROR : " + exception.getMessage());
    }

    public abstract void onReceive(T response);
}
