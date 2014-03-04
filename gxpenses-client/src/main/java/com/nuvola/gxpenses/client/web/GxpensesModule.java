package com.nuvola.gxpenses.client.web;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.web.application.ApplicationModule;
import com.nuvola.gxpenses.client.web.welcome.WelcomeModule;

public class GxpensesModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new WelcomeModule());
        install(new ApplicationModule());

        bindPresenter(GxpensesPresenter.class, GxpensesPresenter.MyView.class, GxpensesView.class,
                GxpensesPresenter.MyProxy.class);
    }
}
