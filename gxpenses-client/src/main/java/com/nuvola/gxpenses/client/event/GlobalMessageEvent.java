package com.nuvola.gxpenses.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class GlobalMessageEvent extends GwtEvent<GlobalMessageEvent.GlobalMessageHandler> {
    public static Type<GlobalMessageHandler> TYPE = new Type<GlobalMessageHandler>();

    public interface GlobalMessageHandler extends EventHandler {
        void onMessageRecieved(GlobalMessageEvent event);
    }

    private String message;

    public GlobalMessageEvent(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static Type<GlobalMessageHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, String message) {
        source.fireEvent(new GlobalMessageEvent(message));
    }

    @Override
    public Type<GlobalMessageHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(GlobalMessageHandler handler) {
        handler.onMessageRecieved(this);
    }
}
