package com.nuvola.gxpenses.client.web.welcome.entrypoint.register;

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
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.rest.UserService;
import com.nuvola.gxpenses.client.web.welcome.entrypoint.EntryPointPresenter;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.client.util.EditorView;
import com.nuvola.gxpenses.common.shared.business.User;

public class RegisterPresenter extends Presenter<RegisterPresenter.MyView, RegisterPresenter.MyProxy>
        implements RegisterUiHandlers {

    public interface MyView extends View, HasUiHandlers<RegisterUiHandlers>, EditorView<User> {
    }

    @ProxyStandard
    @NameToken(NameTokens.register)
    public interface MyProxy extends ProxyPlace<RegisterPresenter> {
    }

    private final RestDispatchAsync dispatcher;
    private final UserService userService;
    private final PlaceManager placeManager;

    @Inject
    RegisterPresenter(EventBus eventBus,
                      MyView view,
                      MyProxy proxy,
                      RestDispatchAsync dispatcher,
                      UserService userService,
                      PlaceManager placeManager) {
        super(eventBus, view, proxy, EntryPointPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.userService = userService;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void register(User user) {
        dispatcher.execute(userService.createUser(user), new AsyncCallbackImpl<Void>() {
            @Override
            public void onReceive(Void response) {
                PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getLogin()).build();
                placeManager.revealPlace(place);
            }
        });
    }

    @Override
    protected void onReveal() {
        getView().edit(new User());
    }
}
