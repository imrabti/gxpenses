package com.nuvola.gxpenses.client.gin;

import com.nuvola.gxpenses.client.application.ApplicationModule;
import com.nuvola.gxpenses.client.place.ClientPlaceManager;
import com.nuvola.gxpenses.client.place.DefaultPlace;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule(ClientPlaceManager.class));
        install(new ApplicationModule());

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.home);
    }
}
