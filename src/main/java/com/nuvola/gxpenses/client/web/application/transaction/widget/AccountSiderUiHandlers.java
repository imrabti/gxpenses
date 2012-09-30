package com.nuvola.gxpenses.client.web.application.transaction.widget;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

public interface AccountSiderUiHandlers extends UiHandlers {
    void addNewAccount(Widget relativeTo);

    void addNewTransfert(Widget relativeTo);

    void removeAccount(Account account);

    void accountSelected(Account account);

    void filterChange(PeriodType newPeriod, TransactionType newType);
}
