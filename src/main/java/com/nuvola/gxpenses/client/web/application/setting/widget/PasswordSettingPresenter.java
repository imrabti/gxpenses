package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.request.GxpensesRequestFactory;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.request.UserRequest;
import com.nuvola.gxpenses.client.request.proxy.PasswordProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.security.SecurityUtils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class PasswordSettingPresenter extends PresenterWidget<PasswordSettingPresenter.MyView>
        implements PasswordSettingUiHandlers {
    public interface MyView extends View, HasUiHandlers<PasswordSettingUiHandlers> {
        void edit(PasswordProxy password);

        void showErrors(Set<ConstraintViolation<?>> violations);

        void clearErrors();
    }

    private final GxpensesRequestFactory requestFactory;
    private final SecurityUtils securityUtils;
    private final MessageBundle messageBundle;

    private UserRequest currentContext;

    @Inject
    public PasswordSettingPresenter(EventBus eventBus, MyView view, final GxpensesRequestFactory requestFactory,
                                    final MessageBundle messageBundle, final SecurityUtils securityUtils) {
        super(eventBus, view);

        this.requestFactory = requestFactory;
        this.messageBundle = messageBundle;
        this.securityUtils = securityUtils;

        getView().setUiHandlers(this);
    }

    @Override
    public void savePassword(final PasswordProxy password) {
        currentContext.updatePassword(password).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                securityUtils.updatePassword(password.getNewPassword());
                GlobalMessageEvent.fire(this, messageBundle.passwordUpdated());

                initAndEditPassword();
                getView().clearErrors();
            }

            @Override
            public void onConstraintViolation(java.util.Set<ConstraintViolation<?>> violations) {
                getView().showErrors(violations);
            }
        });
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        initAndEditPassword();
        getView().clearErrors();
    }

    private void initAndEditPassword() {
        currentContext = requestFactory.userService();
        PasswordProxy password = currentContext.create(PasswordProxy.class);
        getView().edit(password);
    }
}
