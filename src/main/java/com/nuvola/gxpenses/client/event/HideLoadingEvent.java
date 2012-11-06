package com.nuvola.gxpenses.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class HideLoadingEvent extends GwtEvent<HideLoadingEvent.HideLoadingHandler> {

    public interface HideLoadingHandler extends EventHandler {
        void onHideLoading(HideLoadingEvent event);
    }

    private static final Type<HideLoadingHandler> TYPE = new Type<HideLoadingHandler>();

    public static Type<HideLoadingHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new HideLoadingEvent());
    }

    @Override
    public Type<HideLoadingHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(HideLoadingHandler handler) {
        handler.onHideLoading(this);
    }

}
