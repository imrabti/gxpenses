package com.nuvola.gxpenses.client.gin;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.rest.SessionService;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.User;

public class BootstrapperImpl implements Bootstrapper {
    private final PlaceManager placeManager;
    private final RestDispatchAsync dispatcher;
    private final SessionService sessionService;
    private final ValueListFactory valueListFactory;
    private final SuggestionListFactory suggestionListFactory;
    private final SecurityUtils securityUtils;
    private final AsyncCallback<User> getCurrentUserCallback;
    private final CurrentUser currentUser;

    @Inject
    BootstrapperImpl(RestDispatchAsync dispatcher,
                     SessionService sessionService,
                     SecurityUtils securityUtils,
                     PlaceManager placeManager,
                     ValueListFactory valueListFactory,
                     SuggestionListFactory suggestionListFactory,
                     CurrentUser currentUserProvider) {
        this.dispatcher = dispatcher;
        this.sessionService = sessionService;
        this.securityUtils = securityUtils;
        this.placeManager = placeManager;
        this.valueListFactory = valueListFactory;
        this.suggestionListFactory = suggestionListFactory;
        this.currentUser = currentUserProvider;

        getCurrentUserCallback = new AsyncCallbackImpl<User>() {
            @Override
            public void onReceive(User response) {
                currentUser.init(response);
                onGetCurrentUser();
            }
        };
    }

    @Override
    public void onBootstrap() {
        if (securityUtils.isLoggedIn()) {
            dispatcher.execute(sessionService.getSession(), getCurrentUserCallback);
        } else {
            bounceToLogin();
        }
    }

    private void onGetCurrentUser() {
        if (currentUser == null) {
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
        PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getTransaction()).build();
        placeManager.revealPlace(place);
    }

    private void bounceToLogin() {
        PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getLogin()).build();
        placeManager.revealPlace(place);
    }
}
