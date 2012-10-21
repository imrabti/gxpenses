package com.nuvola.gxpenses.client.web.welcome.entrypoint;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EntryPointModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(EntryPointPresenter.class, EntryPointPresenter.MyView.class, EntryPointView.class,
                EntryPointPresenter.MyProxy.class);
    }

}
