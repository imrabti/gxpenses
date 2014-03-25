package com.nuvola.gxpenses.client.gin;

import java.util.List;

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.security.LoggedInGatekeeper;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.web.GxpensesModule;
import com.nuvola.gxpenses.common.client.CommonModule;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule());
        install(new CommonModule());
        install(new GxpensesModule());
        install(new RestDispatchAsyncModule());

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(RestApplicationPath.class).to("rest");

        bind(Resources.class).in(Singleton.class);
        bind(MessageBundle.class).in(Singleton.class);

        bind(SuggestionListFactory.class).in(Singleton.class);
        bind(ValueListFactory.class).in(Singleton.class);
        bind(SecurityUtils.class).in(Singleton.class);
        bind(LoggedInGatekeeper.class).in(Singleton.class);

        bind(String.class).annotatedWith(Currency.class).toProvider(CurrencyProvider.class);
        bind(Integer.class).annotatedWith(PageSize.class).toProvider(PageSizeProvider.class);
        bind(List.class).annotatedWith(ChartColor.class).toProvider(ChartColorProvider.class);

        bind(ResourceLoader.class).asEagerSingleton();
        bind(CurrentUser.class).asEagerSingleton();
    }
}
