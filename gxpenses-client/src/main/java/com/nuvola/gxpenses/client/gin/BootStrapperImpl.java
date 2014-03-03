package com.nuvola.gxpenses.client.gin;

import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.dispatch.rest.shared.RestCallback;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.rest.SessionService;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.common.shared.business.User;

import java.util.logging.Logger;

public class BootstrapperImpl implements Bootstrapper {
    private final static Logger logger = Logger.getLogger(BootstrapperImpl.class.getName());

    private final PlaceManager placeManager;
    private final RestDispatchAsync dispatcher;
    private final SessionService sessionService;
    private final ValueListFactory valueListFactory;
    private final SuggestionListFactory suggestionListFactory;
    private final SecurityUtils securityUtils;
    private final Receiver<User> getCurrentUserCallback;

    @Inject
    BootstrapperImpl(RestDispatchAsync dispatcher,
                     SessionService sessionService,
                     SecurityUtils securityUtils,
                     PlaceManager placeManager,
                     ValueListFactory valueListFactory,
                     SuggestionListFactory suggestionListFactory) {
        this.dispatcher = dispatcher;
        this.sessionService = sessionService;
        this.securityUtils = securityUtils;
        this.placeManager = placeManager;
        this.valueListFactory = valueListFactory;
        this.suggestionListFactory = suggestionListFactory;

        getCurrentUserCallback = new Receiver<User>() {
            @Override
            public void onSuccess(User user) {
                onGetCurrentUser();
            }
        };
    }

    @Override
    public void onBootstrap() {
        if (securityUtils.isLoggedIn()) {
            dispatcher.execute(sessionService.getSession(), RestCallback)

            requestFactory.authenticationService().currentUser().fire(getCurrentUserCallback);
        } else {
            bounceToLogin();
        }
    }

    @Override
    public void logout() {
        securityUtils.clearCredentials();
        bounceToLogin();
    }

    private void onGetCurrentUser() {
        if (currentUser == null) {
            logger.info("User is not authentified -- access denied...");
            bounceToLogin();
        } else {
            suggestionListFactory.getListPayee();
            suggestionListFactory.getListTags();
            valueListFactory.getListAccounts();

            if (placeManager.getCurrentPlaceRequest().matchesNameToken(NameTokens.getLogin())) {
                bounceToTransactions();
            } else {
                placeManager.revealCurrentPlace();
            }
        }
    }

    private void bounceToTransactions() {
        PlaceRequest place = new PlaceRequest(NameTokens.getTransaction());
        placeManager.revealPlace(place);
    }

    private void bounceToLogin() {
        PlaceRequest place = new PlaceRequest(NameTokens.getLogin());
        placeManager.revealPlace(place);
    }
}
