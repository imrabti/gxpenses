package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.SessionService;
import com.nuvola.gxpenses.client.rest.UserService;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.business.User;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GeneralSettingPresenter extends PresenterWidget<GeneralSettingPresenter.MyView>
        implements GeneralSettingUiHandlers {

    public interface MyView extends View, HasUiHandlers<GeneralSettingUiHandlers> {
        void edit(User user);

        void showErrors(Set<ConstraintViolation<?>> violations);

        void clearErrors();
    }

    private final RestDispatchAsync dispatcher;
    private final UserService userService;
    private final SessionService sessionService;
    private final SecurityUtils securityUtils;
    private final MessageBundle messageBundle;

    @Inject
    GeneralSettingPresenter(EventBus eventBus,
                            MyView view,
                            RestDispatchAsync dispatcher,
                            UserService userService,
                            SessionService sessionService,
                            MessageBundle messageBundle,
                            SecurityUtils securityUtils) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.userService = userService;
        this.sessionService = sessionService;
        this.messageBundle = messageBundle;
        this.securityUtils = securityUtils;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveSetting(final User editedUser) {
        dispatcher.execute(userService.updateUser(editedUser), new AsyncCallbackImpl<Void>() {
            @Override
            public void onReceive(Void response) {
                securityUtils.updateUsername(editedUser.getEmail());
                GlobalMessageEvent.fire(this, messageBundle.settingsUpdated());

                initAndEditUser();
                getView().clearErrors();
            }
        });
    }

    @Override
    protected void onReveal() {
        initAndEditUser();
        getView().clearErrors();
    }

    private void initAndEditUser() {
        dispatcher.execute(sessionService.getSession(), new AsyncCallbackImpl<User>() {
            @Override
            public void onReceive(User response) {
                getView().edit(response);
            }
        });
    }
}
