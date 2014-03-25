package com.nuvola.gxpenses.client.web.welcome.entrypoint.login;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.nuvola.gxpenses.client.gin.CurrentUser;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.rest.SessionService;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.EntryPointPresenter;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.client.util.EditorView;
import com.nuvola.gxpenses.common.shared.business.User;
import com.nuvola.gxpenses.common.shared.dto.UserCredentials;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements LoginUiHandlers {
    public interface MyView extends View, HasUiHandlers<LoginUiHandlers>, EditorView<UserCredentials> {
        void displayLoginError(Boolean visible);
    }

    @ProxyStandard
    @NameToken(NameTokens.login)
    public interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private final RestDispatchAsync dispatcher;
    private final SessionService sessionService;
    private final CurrentUser currentUser;
    private final SecurityUtils securityUtils;
    private final PlaceManager placeManager;

    @Inject
    LoginPresenter(EventBus eventBus,
                   MyView view,
                   MyProxy proxy,
                   RestDispatchAsync dispatcher,
                   SessionService sessionService,
                   SecurityUtils securityUtils,
                   PlaceManager placeManager,
                   CurrentUser currentUser) {
        super(eventBus, view, proxy, EntryPointPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.sessionService = sessionService;
        this.securityUtils = securityUtils;
        this.currentUser = currentUser;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void login(final UserCredentials credentials) {
        dispatcher.execute(sessionService.createNewSession(credentials), new AsyncCallbackImpl<Boolean>() {
            @Override
            public void onReceive(Boolean response) {
                if (response) {
                    securityUtils.setCredentials(credentials.getUsername(), credentials.getPassword());
                    initCurrentUser();
                } else {
                    getView().displayLoginError(true);
                }
            }
        });
    }

    @Override
    protected void onReveal() {
        getView().edit(new UserCredentials());
    }

    private void initCurrentUser() {
        dispatcher.execute(sessionService.getSession(), new AsyncCallbackImpl<User>() {
            @Override
            public void onReceive(User response) {
                currentUser.init(response);

                PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getTransaction()).build();
                placeManager.revealPlace(place);
            }
        });
    }
}
