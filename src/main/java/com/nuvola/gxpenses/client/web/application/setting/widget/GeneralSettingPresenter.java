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
import com.nuvola.gxpenses.client.request.proxy.UserProxy;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.security.SecurityUtils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GeneralSettingPresenter extends PresenterWidget<GeneralSettingPresenter.MyView>
        implements GeneralSettingUiHandlers {

    public interface MyView extends View, HasUiHandlers<GeneralSettingUiHandlers> {
        void edit(UserProxy user);

        void showErrors(Set<ConstraintViolation<?>> violations);

        void clearErrors();
    }

    private final GxpensesRequestFactory requestFactory;
    private final SecurityUtils securityUtils;
    private final MessageBundle messageBundle;

    private UserRequest currentContext;

    @Inject
    public GeneralSettingPresenter(EventBus eventBus, MyView view, final GxpensesRequestFactory requestFactory,
                                   final MessageBundle messageBundle, final SecurityUtils securityUtils) {
        super(eventBus, view);

        this.requestFactory = requestFactory;
        this.messageBundle = messageBundle;
        this.securityUtils = securityUtils;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveSetting(final UserProxy editedUser) {
        currentContext.updateUser(editedUser).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                securityUtils.updateUsername(editedUser.getEmail());
                GlobalMessageEvent.fire(this, messageBundle.settingsUpdated());

                initAndEditUser();
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

        initAndEditUser();
        getView().clearErrors();
    }

    private void initAndEditUser() {
        requestFactory.authenticationService().currentUser().fire(new ReceiverImpl<UserProxy>() {
            @Override
            public void onSuccess(UserProxy userProxy) {
                currentContext = requestFactory.userService();
                userProxy = currentContext.edit(userProxy);
                getView().edit(userProxy);
            }
        });
    }
}
