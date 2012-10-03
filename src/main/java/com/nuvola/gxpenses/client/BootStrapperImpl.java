package com.nuvola.gxpenses.client;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.rest.MethodCallBackImpl;
import com.nuvola.gxpenses.client.rest.UserService;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.shared.domaine.User;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.logging.Logger;

public class BootStrapperImpl implements BootStrapper {

    private final static Logger logger = Logger.getLogger(BootStrapperImpl.class.getName());

    private final PlaceManager placeManager;
    private final UserService userService;
    private final SuggestionListFactory suggestionListFactory;

    private final MethodCallback<User> getCurrentUserCallback;

    private User currentUser;

    @Inject
    public BootStrapperImpl(final UserService userService, final PlaceManager placeManager,
                            final SuggestionListFactory suggestionListFactory) {
        this.userService = userService;
        this.placeManager = placeManager;
        this.suggestionListFactory = suggestionListFactory;

        getCurrentUserCallback = new MethodCallBackImpl<User>() {
            @Override
            public void onSuccess(Method method, User user) {
                currentUser = user;
                onGetCurrentUser();
            }
        };
    }

    @Override
    public void init() {
        userService.getLoggedInUser(getCurrentUserCallback);
    }

    @Override
    public void logout() {
        // TODO : call service clear session
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    private void onGetCurrentUser() {
        if (currentUser == null) {
            logger.info("User is not authentified -- access denied...");
            PlaceRequest place = new PlaceRequest(NameTokens.getLogin());
            placeManager.revealPlace(place);
        } else {
            suggestionListFactory.getListPayee();
            suggestionListFactory.getListTags();

            placeManager.revealCurrentPlace();
        }
    }

}
