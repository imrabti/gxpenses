package com.nuvola.gxpenses.client.web.application.transaction.widget;

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
import com.nuvola.gxpenses.client.gin.Currency;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.resource.AccountListStyle;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.web.application.renderer.EnumRenderer;
import com.nuvola.gxpenses.client.web.application.transaction.renderer.AccountCellFactory;
import com.nuvola.gxpenses.shared.domaine.Account;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

import java.util.Arrays;
import java.util.List;

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
    public AccountSiderView(final Binder uiBinder,
                            final UiHandlersStrategy<AccountSiderUiHandlers> uiHandlers,
                            final AccountCellFactory accountCellFactory,
                            final AccountListStyle accountListResources,
                            final Resources resources,
                            @Currency String currency) {
        super(uiHandlers);

        this.resources = resources;
        this.keyProvider = setupKeyProvider();
        this.dataProvider = new ListDataProvider<Account>(keyProvider);

        periodType = new ValueListBox<PeriodType>(new EnumRenderer<PeriodType>());
        transactionType = new ValueListBox<TransactionType>(new EnumRenderer<TransactionType>());
        accountList = new CellList<Account>(accountCellFactory.create(setupRemoveAction()), accountListResources);
        selectionModel = new SingleSelectionModel<Account>(keyProvider);

        //Initialize ValueListBox elements
        periodType.setValue(PeriodType.THIS_MONTH);
        transactionType.setValue(TransactionType.ALL);
        periodType.setAcceptableValues(Arrays.asList(PeriodType.values()));
        transactionType.setAcceptableValues(Arrays.asList(TransactionType.values()));

        //Init The UI Binder
        initWidget(uiBinder.createAndBindUi(this));

        //Set up CSS Style Classes
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
