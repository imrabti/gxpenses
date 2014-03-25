/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.client.web.application.transaction.widget;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.resource.style.list.SiderListStyle;
import com.nuvola.gxpenses.client.web.application.renderer.EnumRenderer;
import com.nuvola.gxpenses.client.web.application.transaction.renderer.AccountCellFactory;
import com.nuvola.gxpenses.common.shared.business.Account;
import com.nuvola.gxpenses.common.shared.type.PeriodType;
import com.nuvola.gxpenses.common.shared.type.TransactionType;

public class AccountSiderView extends ViewWithUiHandlers<AccountSiderUiHandlers>
        implements AccountSiderPresenter.MyView {

    public interface Binder extends UiBinder<Widget, AccountSiderView> {
    }

    @UiField(provided = true)
    ValueListBox<PeriodType> periodType;
    @UiField(provided = true)
    ValueListBox<TransactionType> transactionType;
    @UiField(provided = true)
    CellList<Account> accountList;
    @UiField
    Button addNew;
    @UiField
    Button transfer;

    private final ProvidesKey<Account> keyProvider;
    private final ListDataProvider<Account> dataProvider;
    private final SingleSelectionModel<Account> selectionModel;
    private final Resources resources;

    @Inject
    AccountSiderView(Binder uiBinder,
                     AccountCellFactory accountCellFactory,
                     SiderListStyle siderListResources,
                     Resources resources) {
        this.resources = resources;
        this.keyProvider = setupKeyProvider();
        this.dataProvider = new ListDataProvider<>(keyProvider);

        periodType = new ValueListBox<>(new EnumRenderer<PeriodType>());
        transactionType = new ValueListBox<>(new EnumRenderer<TransactionType>());
        accountList = new CellList<>(accountCellFactory.create(setupRemoveAction()), siderListResources);
        selectionModel = new SingleSelectionModel<>(keyProvider);

        // Initialize ValueListBox elements
        periodType.setValue(PeriodType.THIS_MONTH);
        transactionType.setValue(TransactionType.ALL);
        periodType.setAcceptableValues(Arrays.asList(PeriodType.values()));
        transactionType.setAcceptableValues(Arrays.asList(TransactionType.values()));

        // Init The UI Binder
        initWidget(uiBinder.createAndBindUi(this));

        // Set up CSS Style Classes
        addNew.setStylePrimaryName(resources.buttonStyleCss().button());
        addNew.setStyleName(resources.buttonStyleCss().medium(), true);
        addNew.setStyleName(resources.buttonStyleCss().gray(), true);

        accountList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
        accountList.setSelectionModel(selectionModel);
        dataProvider.addDataDisplay(accountList);

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                getUiHandlers().accountSelected(selectionModel.getSelectedObject());
            }
        });
    }

    @Override
    public void setData(List<Account> accounts) {
        dataProvider.getList().clear();
        dataProvider.getList().addAll(accounts);
        dataProvider.refresh();
    }

    @Override
    public void showTransferButton(Boolean visible) {
        transfer.setVisible(visible);
    }

    @Override
    public void switchTransferStyle() {
        if (!transfer.getText().equals("")) {
            transfer.setText("");
            transfer.removeStyleName(resources.buttonStyleCss().transfertButtonText());
            transfer.addStyleName(resources.buttonStyleCss().transfertButton());
        }
    }

    @Override
    public void clearSelection() {
        selectionModel.clear();
    }

    @UiHandler("addNew")
    void onAddAccount(ClickEvent event) {
        getUiHandlers().addNewAccount((Widget) event.getSource());
    }

    @UiHandler("transfer")
    void onTransfer(ClickEvent event) {
        getUiHandlers().addNewTransfert((Widget) event.getSource());

        transfer.removeStyleName(resources.buttonStyleCss().transfertButton());
        transfer.addStyleName(resources.buttonStyleCss().transfertButtonText());
        transfer.setText("Transfer");
    }

    @UiHandler("periodType")
    void onPeriodTypeChanged(ValueChangeEvent<PeriodType> event) {
        getUiHandlers().filterChange(event.getValue(), transactionType.getValue());
    }

    @UiHandler("transactionType")
    void onTransactionTypeChanged(ValueChangeEvent<TransactionType> event) {
        getUiHandlers().filterChange(periodType.getValue(), event.getValue());
    }

    private ProvidesKey<Account> setupKeyProvider() {
        return new ProvidesKey<Account>() {
            @Override
            public Object getKey(Account account) {
                return account == null ? null : account.getId();
            }
        };
    }

    private ActionCell.Delegate<Account> setupRemoveAction() {
        return new ActionCell.Delegate<Account>() {
            @Override
            public void execute(Account object) {
                getUiHandlers().removeAccount(object);
            }
        };
    }
}
