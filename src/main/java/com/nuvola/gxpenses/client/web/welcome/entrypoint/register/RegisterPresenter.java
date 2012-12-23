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
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.UserRequest;
import com.nuvola.gxpenses.client.request.proxy.UserProxy;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.EntryPointPresenter;

public class RegisterPresenter extends Presenter<RegisterPresenter.MyView, RegisterPresenter.MyProxy>
        implements RegisterUiHandlers {

    public interface MyView extends View, HasUiHandlers<RegisterUiHandlers>, EditorView<UserProxy> {
    }

    @ProxyStandard
    @NameToken(NameTokens.register)
    public interface MyProxy extends ProxyPlace<RegisterPresenter> {
    }

    private final GxpensesRequestFactory requestFactory;
    private final PlaceManager placeManager;

    private UserRequest currentContext;

    @Inject
    public RegisterPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                             final GxpensesRequestFactory requestFactory, final PlaceManager placeManager) {
        super(eventBus, view, proxy);

        this.requestFactory = requestFactory;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void register(UserProxy user) {
        currentContext.createUser(user).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
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

        currentContext = requestFactory.userService();
        getView().edit(currentContext.create(UserProxy.class));
    }
}
