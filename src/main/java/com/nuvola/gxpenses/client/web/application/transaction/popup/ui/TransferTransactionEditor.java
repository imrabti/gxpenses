package com.nuvola.gxpenses.client.web.application.transaction.popup.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.application.transaction.renderer.AccountRenderer;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.dto.TransferTransaction;

public class TransferTransactionEditor extends Composite implements EditorView<TransferTransaction> {

    public interface Binder extends UiBinder<HTMLPanel, TransferTransactionEditor> {
    }

    public interface TransferTransactionDriver extends
            SimpleBeanEditorDriver<TransferTransaction, TransferTransactionEditor> {
    }

    @UiField(provided=true)
    @Ignore
    ValueListBox<Account> sourceAccount;

    @UiField(provided=true)
    @Ignore
    ValueListBox<Account> targetAccount;

    @UiField
    DoubleBox amount;

    private TransferTransactionDriver driver;

    @Inject
    public TransferTransactionEditor(final Binder uiBinder, final TransferTransactionDriver driver) {
        this.driver = driver;
        sourceAccount = new ValueListBox<Account>(new AccountRenderer());
        targetAccount = new ValueListBox<Account>(new AccountRenderer());

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    public void edit(TransferTransaction transfertTransaction) {
        amount.setFocus(true);
        driver.edit(transfertTransaction);
    }

    public TransferTransaction get() {
        TransferTransaction transfertTransaction = driver.flush();
        if(driver.hasErrors()) {
            return null;
        } else {
            return transfertTransaction;
        }
    }

}
