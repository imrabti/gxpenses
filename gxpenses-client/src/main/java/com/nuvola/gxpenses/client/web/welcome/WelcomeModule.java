package com.nuvola.gxpenses.client.web.welcome;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.EntryPointModule;

public class WelcomeModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new EntryPointModule());
    }

}
