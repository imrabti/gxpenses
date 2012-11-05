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
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.security.AuthenticationService;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.EntryPointPresenter;
import com.nuvola.gxpenses.shared.dto.UserCredentials;
import org.fusesource.restygwt.client.Method;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements LoginUiHandlers {

    public interface MyView extends View, HasUiHandlers<LoginUiHandlers>, EditorView<UserCredentials> {
        void displayLoginError(Boolean visible);
    }

    @ProxyStandard
    @NameToken(NameTokens.login)
    public interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private final AuthenticationService authenticationService;
    private final BootStrapper bootStrapper;
    private final SecurityUtils securityUtils;

    @Inject
    public LoginPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                          final AuthenticationService authenticationService, final SecurityUtils securityUtils,
                          final BootStrapper bootStrapper) {
        super(eventBus, view, proxy);

        this.authenticationService = authenticationService;
        this.securityUtils = securityUtils;
        this.bootStrapper = bootStrapper;

        getView().setUiHandlers(this);
    }

    @Override
    public void login(final UserCredentials credentials) {
        authenticationService.authenticate(credentials, new MethodCallbackImpl<Boolean>() {
            @Override
            public void onSuccess(Method method, Boolean authenticated) {
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
