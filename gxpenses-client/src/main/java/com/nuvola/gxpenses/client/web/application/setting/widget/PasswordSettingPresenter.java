package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.UserService;
import com.nuvola.gxpenses.client.security.SecurityUtils;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.shared.dto.Password;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class PasswordSettingPresenter extends PresenterWidget<PasswordSettingPresenter.MyView>
        implements PasswordSettingUiHandlers {
    public interface MyView extends View, HasUiHandlers<PasswordSettingUiHandlers> {
        void edit(Password password);

        void showErrors(Set<ConstraintViolation<?>> violations);

        void clearErrors();
    }

    private final RestDispatchAsync dispatcher;
    private final UserService userService;
    private final SecurityUtils securityUtils;
    private final MessageBundle messageBundle;

    @Inject
    PasswordSettingPresenter(EventBus eventBus,
                             MyView view,
                             RestDispatchAsync dispatcher,
                             UserService userService,
                             MessageBundle messageBundle,
                             SecurityUtils securityUtils) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.userService = userService;
        this.messageBundle = messageBundle;
        this.securityUtils = securityUtils;

        getView().setUiHandlers(this);
    }

    @Override
    public void savePassword(final Password password) {
        dispatcher.execute(userService.password().updatePassword(password), new AsyncCallbackImpl<Void>() {
            @Override
            public void onReceive(Void response) {
                securityUtils.updatePassword(password.getNewPassword());
                GlobalMessageEvent.fire(this, messageBundle.passwordUpdated());

                initAndEditPassword();
                getView().clearErrors();
            }
        });
    }

    @Override
    protected void onReveal() {
        initAndEditPassword();
        getView().clearErrors();
    }

    private void initAndEditPassword() {
        Password password = new Password();
        getView().edit(password);
    }
}
