package com.nuvola.gxpenses.client.web.welcome.entrypoint;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.login.LoginPresenter;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.login.LoginView;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.register.RegisterPresenter;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.register.RegisterView;

public class EntryPointModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(EntryPointPresenter.class, EntryPointPresenter.MyView.class, EntryPointView.class,
                EntryPointPresenter.MyProxy.class);
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class,
                LoginPresenter.MyProxy.class);
        bindPresenter(RegisterPresenter.class, RegisterPresenter.MyView.class, RegisterView.class,
                RegisterPresenter.MyProxy.class);
    }
}
