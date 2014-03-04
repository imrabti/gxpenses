package com.nuvola.gxpenses.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class PopupClosedEvent extends GwtEvent<PopupClosedEvent.PopupClosedHandler> {
    public static Type<PopupClosedHandler> TYPE = new Type<PopupClosedHandler>();

    public interface PopupClosedHandler extends EventHandler {
        void onPopupClosed(PopupClosedEvent event);
    }

    public PopupClosedEvent() {
    }

    public static Type<PopupClosedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new PopupClosedEvent());
    }

    @Override
    public Type<PopupClosedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PopupClosedHandler handler) {
        handler.onPopupClosed(this);
    }
}
