package com.nuvola.gxpenses.client.web.application.transaction.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.nuvola.gxpenses.common.shared.business.Account;
import com.nuvola.gxpenses.common.shared.type.PeriodType;
import com.nuvola.gxpenses.common.shared.type.TransactionType;

public class AccountChangedEvent extends GwtEvent<AccountChangedEvent.AccountChangedHandler> {
    public static Type<AccountChangedHandler> TYPE = new Type<AccountChangedHandler>();

    public interface AccountChangedHandler extends EventHandler {
        void onAccountChanged(AccountChangedEvent event);
    }

    private Account account;
    private PeriodType periodeFilter;
    private TransactionType typeFilter;

    public AccountChangedEvent() {
    }

    public AccountChangedEvent(Account account,
                               PeriodType periodeFilter,
                               TransactionType typeFilter) {
        this.account = account;
        this.periodeFilter = periodeFilter;
        this.typeFilter = typeFilter;
    }

    public Account getAccount() {
        return account;
    }

    public PeriodType getPeriodeFilter() {
        return periodeFilter;
    }

    public TransactionType getTypeFilter() {
        return typeFilter;
    }

    @Override
    public Type<AccountChangedHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<AccountChangedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new AccountChangedEvent());
    }

    public static void fire(HasHandlers source, Account account, PeriodType periodeFilter, TransactionType typeFilter) {
        source.fireEvent(new AccountChangedEvent(account, periodeFilter, typeFilter));
    }

    @Override
    protected void dispatch(AccountChangedHandler handler) {
        handler.onAccountChanged(this);
    }
}
