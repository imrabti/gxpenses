package com.nuvola.gxpenses.client.web.welcome.entrypoint.login;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.nuvola.gxpenses.client.BootStrapper;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.EntryPointPresenter;
import com.nuvola.gxpenses.shared.dto.UserCredentials;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements LoginUiHandlers {
    public interface MyView extends View, HasUiHandlers<LoginUiHandlers>, EditorView<UserCredentials> {
        void displayLoginError(Boolean visible);
    }

    @ProxyStandard
    @NameToken(NameTokens.login)
    public interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private final GxpensesRequestFactory requestFactory;
    private final BootStrapper bootStrapper;
    private final SecurityUtils securityUtils;

    @Inject
    public LoginPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                          final GxpensesRequestFactory requestFactory, final SecurityUtils securityUtils,
                          final BootStrapper bootStrapper) {
        super(eventBus, view, proxy);

        this.requestFactory = requestFactory;
        this.securityUtils = securityUtils;
        this.bootStrapper = bootStrapper;

        getView().setUiHandlers(this);
    }

    @Override
    public void login(final UserCredentials credentials) {
        requestFactory.authenticationService().authenticate(credentials.getUsername(), credentials.getPassword())
                .fire(new ReceiverImpl<Boolean>() {
            @Override
            public void onSuccess(Boolean authenticated) {
                if (authenticated) {
                    securityUtils.setCredentials(credentials.getUsername(), credentials.getPassword());
                    bootStrapper.init();
                } else {
                    getView().displayLoginError(true);
                }
            }
        });
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, EntryPointPresenter.TYPE_SetMainContent, this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        getView().edit(new UserCredentials());
    }
}
