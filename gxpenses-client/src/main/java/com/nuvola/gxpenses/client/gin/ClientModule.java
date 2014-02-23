package com.nuvola.gxpenses.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.nuvola.gxpenses.client.BootStrapper;
import com.nuvola.gxpenses.client.BootStrapperImpl;
import com.nuvola.gxpenses.client.event.EventSourceRequestTransport;
import com.nuvola.gxpenses.client.place.ClientPlaceManager;
import com.nuvola.gxpenses.client.place.DefaultPlace;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.security.LoggedInGatekeeper;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.web.GxpensesModule;

import java.util.List;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule(ClientPlaceManager.class));
        install(new GxpensesModule());

        bind(Resources.class).in(Singleton.class);
        bind(MessageBundle.class).in(Singleton.class);
        requestStaticInjection(ReceiverImpl.class);

        bind(GxpensesRequestFactory.class).toProvider(RequestFactoryProvider.class).in(Singleton.class);
        bind(BootStrapper.class).to(BootStrapperImpl.class).in(Singleton.class);
        bind(SuggestionListFactory.class).in(Singleton.class);
        bind(ValueListFactory.class).in(Singleton.class);
        bind(SecurityUtils.class).in(Singleton.class);
        bind(LoggedInGatekeeper.class).in(Singleton.class);

        bind(String.class).annotatedWith(Currency.class).toProvider(CurrencyProvider.class);
        bind(Integer.class).annotatedWith(PageSize.class).toProvider(PageSizeProvider.class);
        bind(List.class).annotatedWith(ChartColor.class).toProvider(ChartColorProvider.class);
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
    }

    static class RequestFactoryProvider implements Provider<GxpensesRequestFactory> {
        private final GxpensesRequestFactory requestFactory;

        @Inject
        public RequestFactoryProvider(EventBus eventBus, SecurityUtils securityUtils) {
            requestFactory = GWT.create(GxpensesRequestFactory.class);
            requestFactory.initialize(eventBus, new EventSourceRequestTransport(eventBus, securityUtils));
        }

        public GxpensesRequestFactory get() {
            return requestFactory;
        }
    }
}
