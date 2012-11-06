package com.nuvola.gxpenses.client.web.welcome.entrypoint.register;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.security.RegistrationService;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.EntryPointPresenter;
import com.nuvola.gxpenses.shared.domaine.User;

public class RegisterPresenter extends Presenter<RegisterPresenter.MyView, RegisterPresenter.MyProxy>
        implements RegisterUiHandlers {

    public interface MyView extends View, HasUiHandlers<RegisterUiHandlers>, EditorView<User> {
    }

    @ProxyStandard
    @NameToken(NameTokens.register)
    public interface MyProxy extends ProxyPlace<RegisterPresenter> {
    }

    private final RegistrationService registrationService;
    private final PlaceManager placeManager;

    @Inject
    public RegisterPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                             final RegistrationService registrationService, final PlaceManager placeManager) {
        super(eventBus, view, proxy);

        this.registrationService = registrationService;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void register(User user) {
        registrationService.register(user, new MethodCallbackImpl<Void>() {
            @Override
            public void handleSuccess(Void aVoid) {
                PlaceRequest place = new PlaceRequest(NameTokens.getLogin());
                placeManager.revealPlace(place);
            }
        });
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, EntryPointPresenter.TYPE_SetMainContent, this);
    }

    protected void onReveal() {
        super.onReveal();

        getView().edit(new User());
    }

}
