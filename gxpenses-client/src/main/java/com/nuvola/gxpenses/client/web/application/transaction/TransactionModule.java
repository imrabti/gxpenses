package com.nuvola.gxpenses.client.web.application.transaction;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddAccountPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddAccountView;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddTransactionPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.popup.AddTransactionView;
import com.nuvola.gxpenses.client.web.application.transaction.popup.TransferTransactionPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.popup.TransferTransactionView;
import com.nuvola.gxpenses.client.web.application.transaction.renderer.AccountCellFactory;
import com.nuvola.gxpenses.client.web.application.transaction.renderer.TransactionCellFactory;
import com.nuvola.gxpenses.client.web.application.transaction.widget.AccountSiderPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.widget.AccountSiderView;

public class TransactionModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
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
