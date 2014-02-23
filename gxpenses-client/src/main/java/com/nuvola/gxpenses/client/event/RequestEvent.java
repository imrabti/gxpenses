package com.nuvola.gxpenses.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class RequestEvent extends GwtEvent<RequestEvent.RequestEventHandler> {
    private static final Type<RequestEventHandler> TYPE = new Type<RequestEventHandler>();

    public interface RequestEventHandler extends EventHandler {
        void onRequestEvent(RequestEvent requestEvent);
    }

    public enum State {
        SENT, RECEIVED
    }

    private final State state;

    public RequestEvent(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public Type<RequestEventHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<RequestEventHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, State state) {
        source.fireEvent(new RequestEvent(state));
    }

    @Override
    protected void dispatch(RequestEventHandler requestEventHandler) {
        requestEventHandler.onRequestEvent(this);
    }
}
