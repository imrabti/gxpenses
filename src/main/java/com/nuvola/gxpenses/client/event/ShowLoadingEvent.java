package com.nuvola.gxpenses.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ShowLoadingEvent extends GwtEvent<ShowLoadingEvent.ShowLoadingHandler> {

    public interface ShowLoadingHandler extends EventHandler {
        void onShowLoading(ShowLoadingEvent event);
    }

    private static final Type<ShowLoadingHandler> TYPE = new Type<ShowLoadingHandler>();

    public static Type<ShowLoadingHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new ShowLoadingEvent());
    }

    @Override
    public Type<ShowLoadingHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowLoadingHandler handler) {
        handler.onShowLoading(this);
    }

}
