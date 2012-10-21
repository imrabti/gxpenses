package com.nuvola.gxpenses.client.web.welcome.entrypoint;

import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.login.LoginPresenter;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.login.LoginUiHandlers;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.login.LoginView;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.register.RegisterPresenter;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.register.RegisterUiHandlers;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.register.RegisterView;

public class EntryPointModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<UiHandlersStrategy<LoginUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<LoginUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<RegisterUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<RegisterUiHandlers>>() {});

        bindPresenter(EntryPointPresenter.class, EntryPointPresenter.MyView.class, EntryPointView.class,
                EntryPointPresenter.MyProxy.class);
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class,
                LoginPresenter.MyProxy.class);
        bindPresenter(RegisterPresenter.class, RegisterPresenter.MyView.class, RegisterView.class,
                RegisterPresenter.MyProxy.class);
    }

}
