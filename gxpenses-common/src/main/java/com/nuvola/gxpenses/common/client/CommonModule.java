package com.nuvola.gxpenses.common.client;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;

public class CommonModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        requestStaticInjection(AsyncCallbackImpl.class);
    }
}
