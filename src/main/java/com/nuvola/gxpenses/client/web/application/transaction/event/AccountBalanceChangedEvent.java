package com.nuvola.gxpenses.client.web.application.transaction.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class AccountBalanceChangedEvent extends GwtEvent<AccountBalanceChangedEvent.AccountBalanceChangedHandler> {

    public static Type<AccountBalanceChangedHandler> TYPE = new Type<AccountBalanceChangedHandler>();

    public interface AccountBalanceChangedHandler extends EventHandler {
        void onAccountBalanceChanged(AccountBalanceChangedEvent event);
    }

    public AccountBalanceChangedEvent() {
        super();
    }

    @Override
    public Type<AccountBalanceChangedHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<AccountBalanceChangedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new AccountBalanceChangedEvent());
    }

    @Override
    protected void dispatch(AccountBalanceChangedHandler handler) {
        handler.onAccountBalanceChanged(this);
    }

}
