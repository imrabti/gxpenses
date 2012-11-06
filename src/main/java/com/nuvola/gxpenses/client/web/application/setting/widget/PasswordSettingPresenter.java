package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.rest.SettingService;
import com.nuvola.gxpenses.shared.dto.Password;
import com.nuvola.gxpenses.shared.dto.ValidatedResponse;

public class PasswordSettingPresenter extends PresenterWidget<PasswordSettingPresenter.MyView>
        implements PasswordSettingUiHandlers {

    public interface MyView extends View, HasUiHandlers<PasswordSettingUiHandlers> {
        void edit(Password password);
    }

    private final SettingService settingService;
    private final MessageBundle messageBundle;

    @Inject
    public PasswordSettingPresenter(EventBus eventBus, MyView view, final SettingService settingService,
                                    final MessageBundle messageBundle) {
        super(eventBus, view);

        this.settingService = settingService;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void savePassword(Password password) {
        settingService.updateUserPassword(password, new MethodCallbackImpl<ValidatedResponse<Password>>() {
            @Override
            public void handleSuccess(ValidatedResponse<Password> passwordValidatedResponse) {
                GlobalMessageEvent.fire(this, messageBundle.passwordUpdated());
            }
        });
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        Password password = new Password();
        getView().edit(password);
    }

}
