package com.nuvola.gxpenses.client.web.application.transaction.popup.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.application.renderer.EnumRenderer;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.type.AccountType;

import java.util.Arrays;

public class AccountEditor extends Composite implements EditorView<Account> {

    public interface Binder extends UiBinder<HTMLPanel, AccountEditor> {
    }

    public interface AccountDriver extends SimpleBeanEditorDriver<Account, AccountEditor> {
    }

    @UiField
    TextBox name;

    @UiField(provided=true)
    ValueListBox<AccountType> type;

    @UiField
    DoubleBox balance;

    private final AccountDriver driver;

    @Inject
    public AccountEditor(final Binder uiBinder, final AccountDriver driver) {
        this.driver = driver;

        //Initialize ValusListBox elements
        type = new ValueListBox<AccountType>(new EnumRenderer<AccountType>());
        type.setAcceptableValues(Arrays.asList(AccountType.values()));
        type.setValue(AccountType.CASH);

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    public void edit(Account account) {
        name.setFocus(true);
        driver.edit(account);
    }

    public Account get() {
        Account account = driver.flush();
        if(driver.hasErrors()) {
            return null;
        } else {
            return account;
        }
    }

}
