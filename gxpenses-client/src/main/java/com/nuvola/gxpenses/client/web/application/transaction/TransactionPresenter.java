package com.nuvola.gxpenses.client.web.application.transaction;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.NoElementFoundEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.event.SetVisibleSiderEvent;
import com.nuvola.gxpenses.client.gin.PageSize;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.TransactionRequest;
import com.nuvola.gxpenses.client.request.proxy.AccountProxy;
import com.nuvola.gxpenses.client.request.proxy.DataPageProxy;
import com.nuvola.gxpenses.client.request.proxy.PagedTransactionsProxy;
import com.nuvola.gxpenses.client.request.proxy.TransactionFilterProxy;
import com.nuvola.gxpenses.client.request.proxy.TransactionProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.security.LoggedInGatekeeper;
import com.nuvola.gxpenses.client.util.DateUtils;
import com.nuvola.gxpenses.client.util.EmptyDisplay;
import com.nuvola.gxpenses.client.web.application.ApplicationPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountBalanceChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.event.AccountChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.event.TransactionFiltreChangedEvent;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddTransactionPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.widget.AccountSiderPresenter;
import com.nuvola.gxpenses.shared.type.PeriodType;
import com.nuvola.gxpenses.shared.type.TransactionType;

import java.util.List;

public class TransactionPresenter extends Presenter<TransactionPresenter.MyView, TransactionPresenter.MyProxy>
        implements TransactionUiHandlers, AccountChangedEvent.AccountChangedHandler,
        TransactionFiltreChangedEvent.TransactionFilterChangedHandler,
        AccountBalanceChangedEvent.AccountBalanceChangedHandler,
        NoElementFoundEvent.NoElementFoundHandler, PopupClosedEvent.PopupClosedHandler {
    public interface MyView extends View, EmptyDisplay, HasUiHandlers<TransactionUiHandlers> {
        void setData(List<TransactionProxy> data, Integer start, Integer totalCount);

        void clearSelection();

        void setAccountName(String accountName);

        void setPeriod(String period);

        void setTransactionTotal(Double total);

        void showTransactionsPanel();

        void hideTransactionsPanel();

        void showNoTransactionsPanel();

        void hideNoTransactionsPanel();

        void switchAddTransactionStyle();

        Boolean isEmptyVisible();
    }

    @ProxyStandard
    @NameToken(NameTokens.transaction)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<TransactionPresenter> {
    }

    private final GxpensesRequestFactory requestFactory;
    private final MessageBundle messageBundle;
    private final Integer defaultPageSize;

    private final AccountSiderPresenter accountSiderPresenter;
    private final AddTransactionPresenter addTransactionPresenter;

    private AccountProxy selectedAccount;
    private PeriodType selectedPeriodeFilter;
    private TransactionType selectedTypeFilter;
    private Integer paginationStart;

    @Inject
    public TransactionPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                                final GxpensesRequestFactory requestFactory,
                                final MessageBundle messageBundle,
                                final AccountSiderPresenter accountSiderPresenter,
                                final AddTransactionPresenter addTransactionPresenter,
                                @PageSize Integer defaultPageSize) {
        super(eventBus, view, proxy);

        this.requestFactory = requestFactory;
        this.messageBundle = messageBundle;
        this.defaultPageSize = defaultPageSize;
        this.accountSiderPresenter = accountSiderPresenter;
        this.addTransactionPresenter = addTransactionPresenter;

        getView().setUiHandlers(this);
    }

    @Override
    public void onPopupClosed(PopupClosedEvent event) {
        getView().switchAddTransactionStyle();
    }

    @Override
    public void onNoElementFound(NoElementFoundEvent event) {
        if (event.getSize() == 0) {
            if (!getView().isEmptyVisible()) {
                getView().hideTransactionsPanel();
            }
            getView().setEmptyMessage(messageBundle.noAccounts());
        } else {
            getView().setEmptyMessage(messageBundle.noSelectedAccount());
        }
    }

    @Override
    public void onAccountChanged(AccountChangedEvent event) {
        getView().clearSelection();

        if (event.getAccount() == null) {
            selectedAccount = null;
            selectedPeriodeFilter = event.getPeriodeFilter();
            selectedTypeFilter = event.getTypeFilter();

            if (!getView().isEmptyVisible()) {
                getView().hideTransactionsPanel();
            }
        } else {
            selectedAccount = event.getAccount();
            selectedPeriodeFilter = event.getPeriodeFilter();
            selectedTypeFilter = event.getTypeFilter();
            paginationStart = 0;

            //Set the current Date
            getView().setPeriod(DateUtils.getDateToDisplay(selectedPeriodeFilter));

            //Reload transactions total amount and data
            fireLoadTotalAmountTransactionRequest();
            fireLoadTransactionDataRequest(0, defaultPageSize);

            //Show the transaction Panel
            if (getView().isEmptyVisible()) {
                getView().showTransactionsPanel();
            }

            //Set up account name
            getView().setAccountName(selectedAccount.getName());
        }
    }

    @Override
    public void onFilterChanged(TransactionFiltreChangedEvent event) {
        selectedPeriodeFilter = event.getPeriodeFilter();
        selectedTypeFilter = event.getTypeFilter();
        paginationStart = 0;

        //Set the current Date
        getView().setPeriod(DateUtils.getDateToDisplay(selectedPeriodeFilter));
        getView().clearSelection();

        //Reload transactions total amount and data
        fireLoadTotalAmountTransactionRequest();
        fireLoadTransactionDataRequest(0, defaultPageSize);
    }

    @Override
    public void onAccountBalanceChanged(AccountBalanceChangedEvent event) {
        if (selectedAccount != null) {
            Integer pageNumber = (paginationStart / defaultPageSize) + (paginationStart % defaultPageSize);
            fireLoadTransactionDataRequest(pageNumber, defaultPageSize);
            fireLoadTotalAmountTransactionRequest();
        }
    }

    @Override
    public void loadTransactions(Integer start, Integer length) {
        paginationStart = start;
        Integer pageNumber = (start / length) + (start % length);
        fireLoadTransactionDataRequest(pageNumber, length);
    }

    @Override
    public void addNewTransaction(Widget relativeTo) {
        addTransactionPresenter.setRelativeTo(relativeTo);
        addTransactionPresenter.setSelectedAccount(selectedAccount);
        addToPopupSlot(addTransactionPresenter, false);
    }

    @Override
    public void removeTransaction(TransactionProxy transaction) {
        Boolean decision = Window.confirm(messageBundle.transactionConf());
        if (decision) {
            requestFactory.transactionService().removeTransaction(transaction.getId()).fire(new ReceiverImpl<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Integer pageNumber = (paginationStart / defaultPageSize) + (paginationStart % defaultPageSize);
                    fireLoadTransactionDataRequest(pageNumber, defaultPageSize);
                    fireLoadTotalAmountTransactionRequest();

                    AccountBalanceChangedEvent.fire(this);
                    GlobalMessageEvent.fire(this, messageBundle.transactionRemoved());
                }
            });
        }
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.TYPE_SetMainContent, this);
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(PopupClosedEvent.getType(), this);
        addRegisteredHandler(NoElementFoundEvent.getType(), this);
        addRegisteredHandler(AccountChangedEvent.getType(), this);
        addRegisteredHandler(TransactionFiltreChangedEvent.getType(), this);
        addRegisteredHandler(AccountBalanceChangedEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        SetVisibleSiderEvent.fire(this, accountSiderPresenter);
    }

    private void fireLoadTransactionDataRequest(Integer pageNumber, Integer length) {
        TransactionRequest currentContext = requestFactory.transactionService();
        TransactionFilterProxy filter = currentContext.create(TransactionFilterProxy.class);
        DataPageProxy page = currentContext.create(DataPageProxy.class);
        filter.setAccountId(selectedAccount.getId());
        filter.setType(selectedTypeFilter);
        filter.setPeriod(selectedPeriodeFilter);
        page.setPageNumber(pageNumber);
        page.setLength(length);

        currentContext.findByAccountAndDateAndType(filter, page).fire(new ReceiverImpl<PagedTransactionsProxy>() {
            @Override
            public void onSuccess(PagedTransactionsProxy result) {
                getView().setData(result.getTransactions(), paginationStart, result.getTotalElements());

                if (result.getTransactions().size() > 0) {
                    getView().hideNoTransactionsPanel();
                } else {
                    getView().showNoTransactionsPanel();
                }
            }
        });
    }

    private void fireLoadTotalAmountTransactionRequest() {
        TransactionRequest currentContext = requestFactory.transactionService();
        TransactionFilterProxy filter = currentContext.create(TransactionFilterProxy.class);
        filter.setAccountId(selectedAccount.getId());
        filter.setType(selectedTypeFilter);
        filter.setPeriod(selectedPeriodeFilter);

        currentContext.totalAmountByAccountAndPeriodAndType(filter).fire(new ReceiverImpl<Double>() {
            @Override
            public void onSuccess(Double totalAmount) {
                getView().setTransactionTotal(totalAmount);
            }
        });
    }
}
