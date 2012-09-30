package com.nuvola.gxpenses.client.web.application.transaction;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddAccountPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddAccountUiHandlers;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddAccountView;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddTransactionPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddTransactionUiHandler;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddTransactionView;
import com.nuvola.gxpenses.client.web.application.transaction.popup.TransferTransactionPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.popup.TransferTransactionUiHandlers;
import com.nuvola.gxpenses.client.web.application.transaction.popup.TransferTransactionView;
import com.nuvola.gxpenses.client.web.application.transaction.renderer.AccountCellFactory;
import com.nuvola.gxpenses.client.web.application.transaction.renderer.TransactionCellFactory;
import com.nuvola.gxpenses.client.web.application.transaction.widget.AccountSiderPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.widget.AccountSiderUiHandlers;
import com.nuvola.gxpenses.client.web.application.transaction.widget.AccountSiderView;

public class TransactionModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<UiHandlersStrategy<AccountSiderUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<AccountSiderUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<TransactionUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<TransactionUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<AddAccountUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<AddAccountUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<TransferTransactionUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<TransferTransactionUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<AddTransactionUiHandler>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<AddTransactionUiHandler>>() {});

        bindPresenter(TransactionPresenter.class, TransactionPresenter.MyView.class,
                TransactionView.class, TransactionPresenter.MyProxy.class);

        bindSingletonPresenterWidget(AccountSiderPresenter.class, AccountSiderPresenter.MyView.class,
                AccountSiderView.class);
        bindSingletonPresenterWidget(AddAccountPresenter.class, AddAccountPresenter.MyView.class,
                AddAccountView.class);
        bindSingletonPresenterWidget(TransferTransactionPresenter.class, TransferTransactionPresenter.MyView.class,
                TransferTransactionView.class);
        bindSingletonPresenterWidget(AddTransactionPresenter.class, AddTransactionPresenter.MyView.class,
                AddTransactionView.class);

        install(new GinFactoryModuleBuilder().build(AccountCellFactory.class));
        install(new GinFactoryModuleBuilder().build(TransactionCellFactory.class));
    }

}
