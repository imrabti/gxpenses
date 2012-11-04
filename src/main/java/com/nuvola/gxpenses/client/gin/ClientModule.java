package com.nuvola.gxpenses.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.nuvola.gxpenses.client.BootStrapper;
import com.nuvola.gxpenses.client.BootStrapperImpl;
import com.nuvola.gxpenses.client.place.ClientPlaceManager;
import com.nuvola.gxpenses.client.place.DefaultPlace;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.AccountService;
import com.nuvola.gxpenses.client.rest.BudgetElementService;
import com.nuvola.gxpenses.client.rest.BudgetService;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.rest.SettingService;
import com.nuvola.gxpenses.client.security.AuthenticationService;
import com.nuvola.gxpenses.client.security.SecureDispatcher;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.client.rest.TransactionService;
import com.nuvola.gxpenses.client.rest.UserService;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.client.web.GxpensesModule;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

public class ClientModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule(ClientPlaceManager.class));
        install(new GxpensesModule());

        bind(Resources.class).in(Singleton.class);
        bind(MessageBundle.class).in(Singleton.class);
        requestStaticInjection(MethodCallbackImpl.class);

        bind(BootStrapper.class).to(BootStrapperImpl.class).in(Singleton.class);
        bind(SuggestionListFactory.class).in(Singleton.class);
        bind(ValueListFactory.class).in(Singleton.class);

        bind(String.class).annotatedWith(Currency.class).toProvider(CurrencyProvider.class);
        bind(Integer.class).annotatedWith(PageSize.class).toProvider(PageSizeProvider.class);
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(Names.named("rest")).to("http://127.0.0.1:8888/rest");
    }

    @Provides
    @Singleton
    @Inject
    public AuthenticationService provideAuthenticationService(@Named("rest") String url) {
        AuthenticationService authenticationService = GWT.create(AuthenticationService.class);
        Resource resource = new Resource(url);
        ((RestServiceProxy) authenticationService).setResource(resource);

        return authenticationService;
    }

    @Provides
    @Singleton
    @Inject
    public UserService provideUserService(@Named("rest") String url, SecureDispatcher dispatcher) {
        UserService userService = GWT.create(UserService.class);
        Resource resource = new Resource(url);
        ((RestServiceProxy) userService).setResource(resource);
        ((RestServiceProxy) userService).setDispatcher(dispatcher);

        return userService;
    }

    @Provides
    @Singleton
    @Inject
    public AccountService provideAccountService(@Named("rest") String url, SecureDispatcher dispatcher) {
        AccountService accountService = GWT.create(AccountService.class);
        Resource resource = new Resource(url);
        ((RestServiceProxy) accountService).setResource(resource);
        ((RestServiceProxy) accountService).setDispatcher(dispatcher);

        return accountService;
    }

    @Provides
    @Singleton
    @Inject
    public TransactionService provideTransactionService(@Named("rest") String url, SecureDispatcher dispatcher) {
        TransactionService transactionService = GWT.create(TransactionService.class);
        Resource resource = new Resource(url);
        ((RestServiceProxy) transactionService).setResource(resource);
        ((RestServiceProxy) transactionService).setDispatcher(dispatcher);

        return transactionService;
    }

    @Provides
    @Singleton
    @Inject
    public SettingService provideSettingService(@Named("rest") String url, SecureDispatcher dispatcher) {
        SettingService settingServiceService = GWT.create(SettingService.class);
        Resource resource = new Resource(url);
        ((RestServiceProxy) settingServiceService).setResource(resource);
        ((RestServiceProxy) settingServiceService).setDispatcher(dispatcher);

        return settingServiceService;
    }

    @Provides
    @Singleton
    @Inject
    public BudgetService provideBudgetService(@Named("rest") String url, SecureDispatcher dispatcher) {
        BudgetService budgetService = GWT.create(BudgetService.class);
        Resource resource = new Resource(url);
        ((RestServiceProxy) budgetService).setResource(resource);
        ((RestServiceProxy) budgetService).setDispatcher(dispatcher);

        return budgetService;
    }

    @Provides
    @Singleton
    @Inject
    public BudgetElementService provideBudgetElementService(@Named("rest") String url, SecureDispatcher dispatcher) {
        BudgetElementService budgetElementService = GWT.create(BudgetElementService.class);
        Resource resource = new Resource(url);
        ((RestServiceProxy) budgetElementService).setResource(resource);
        ((RestServiceProxy) budgetElementService).setDispatcher(dispatcher);

        return budgetElementService;
    }

}
