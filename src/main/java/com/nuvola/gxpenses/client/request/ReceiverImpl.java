package com.nuvola.gxpenses.client.request;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

public abstract class ReceiverImpl<T> extends Receiver<T> implements HasHandlers {
    @Inject
    protected static EventBus eventBus;

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }
}
