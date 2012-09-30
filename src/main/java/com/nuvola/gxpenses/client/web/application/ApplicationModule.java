package com.nuvola.gxpenses.client.web.application;

import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.web.application.transaction.TransactionModule;
import com.nuvola.gxpenses.client.web.application.widget.HeaderPresenter;
import com.nuvola.gxpenses.client.web.application.widget.HeaderUiHandlers;
import com.nuvola.gxpenses.client.web.application.widget.HeaderView;

public class ApplicationModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new TransactionModule());

        bind(new TypeLiteral<UiHandlersStrategy<HeaderUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<HeaderUiHandlers>>() {});

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        bindSingletonPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class,
                HeaderView.class);
    }

}
