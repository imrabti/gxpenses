package com.nuvola.gxpenses.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class NoElementFoundEvent extends GwtEvent<NoElementFoundEvent.NoElementFoundHandler> {

    public static Type<NoElementFoundHandler> TYPE = new Type<NoElementFoundHandler>();

    public interface NoElementFoundHandler extends EventHandler {
        void onNoElementFound(NoElementFoundEvent event);
    }

    private int size;

    public NoElementFoundEvent(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public static Type<NoElementFoundHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, int size) {
        source.fireEvent(new NoElementFoundEvent(size));
    }

    @Override
    public Type<NoElementFoundHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(NoElementFoundHandler handler) {
        handler.onNoElementFound(this);
    }
}
