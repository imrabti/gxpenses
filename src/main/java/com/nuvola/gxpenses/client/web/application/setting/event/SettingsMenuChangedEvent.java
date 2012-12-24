package com.nuvola.gxpenses.client.web.application.setting.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderView.SettingsEnum;

public class SettingsMenuChangedEvent extends GwtEvent<SettingsMenuChangedEvent.SettingsChangedEventHandler> {
    public static Type<SettingsChangedEventHandler> TYPE = new Type<SettingsChangedEventHandler>();

    public interface SettingsChangedEventHandler extends EventHandler {
        void onSettingsChanged(SettingsMenuChangedEvent event);
    }

    private SettingsEnum selectedMenu;

    public SettingsMenuChangedEvent(SettingsEnum selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public SettingsEnum getSelectedMenu() {
        return selectedMenu;
    }

    public static Type<SettingsChangedEventHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, SettingsEnum selectedMenu) {
        source.fireEvent(new SettingsMenuChangedEvent(selectedMenu));
    }

    @Override
    public GwtEvent.Type<SettingsChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SettingsChangedEventHandler handler) {
        handler.onSettingsChanged(this);
    }
}
