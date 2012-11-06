package com.nuvola.gxpenses.client;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.client.rest.UserService;
import com.nuvola.gxpenses.client.util.ValueListFactory;
import com.nuvola.gxpenses.shared.domaine.User;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.logging.Logger;

public class BootStrapperImpl implements BootStrapper {

    private final static Logger logger = Logger.getLogger(BootStrapperImpl.class.getName());

    private final PlaceManager placeManager;
    private final UserService userService;
    private final ValueListFactory valueListFactory;
    private final SuggestionListFactory suggestionListFactory;
    private final SecurityUtils securityUtils;

    private final MethodCallback<User> getCurrentUserCallback;

    private User currentUser;

    @Inject
    public BootStrapperImpl(final UserService userService, final PlaceManager placeManager,
                            final SecurityUtils securityUtils,
                            final ValueListFactory valueListFactory,
                            final SuggestionListFactory suggestionListFactory) {
        this.securityUtils = securityUtils;
        this.userService = userService;
        this.placeManager = placeManager;
        this.valueListFactory = valueListFactory;
        this.suggestionListFactory = suggestionListFactory;

        getCurrentUserCallback = new MethodCallbackImpl<User>() {
            @Override
            public void handleSuccess(User user) {
                currentUser = user;
                onGetCurrentUser();
            }
        };
    }

    @Override
    public void init() {
        if (securityUtils.isLoggedIn()) {
            userService.getLoggedInUser(getCurrentUserCallback);
        } else {
            bounceToLogin();
        }
    }

    @Override
    public void logout() {
        securityUtils.clearCredentials();
        bounceToLogin();
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
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
