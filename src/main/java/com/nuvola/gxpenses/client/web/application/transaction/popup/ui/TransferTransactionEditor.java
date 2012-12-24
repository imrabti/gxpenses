package com.nuvola.gxpenses.client.web.application.transaction.popup.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;
import com.nuvola.gxpenses.client.request.proxy.TransferTransactionProxy;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.application.transaction.renderer.AccountRenderer;

public class TransferTransactionEditor extends Composite implements EditorView<TransferTransactionProxy> {

    public interface Binder extends UiBinder<HTMLPanel, TransferTransactionEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<TransferTransactionProxy, TransferTransactionEditor> {
    }

    @UiField(provided = true)
    ValueListBox<AccountProxy> sourceAccount;
    @UiField(provided = true)
    ValueListBox<AccountProxy> targetAccount;
    @UiField
    DoubleBox amount;

    private final ValueListFactory valueListFactory;
    private final Driver driver;

    @Inject
    public TransferTransactionEditor(final Binder uiBinder, final Driver driver,
                                     final ValueListFactory valueListFactory) {
        this.driver = driver;
        this.valueListFactory = valueListFactory;

        sourceAccount = new ValueListBox<AccountProxy>(new AccountRenderer());
        targetAccount = new ValueListBox<AccountProxy>(new AccountRenderer());

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    @Override
    public void edit(TransferTransactionProxy transfertTransaction) {
        sourceAccount.setAcceptableValues(valueListFactory.getListAccounts());
        targetAccount.setAcceptableValues(valueListFactory.getListAccounts());

        amount.setFocus(true);
        driver.edit(transfertTransaction);
    }

    @Override
    public TransferTransactionProxy get() {
        TransferTransactionProxy transfertTransaction = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return transfertTransaction;
        }
    }
}
