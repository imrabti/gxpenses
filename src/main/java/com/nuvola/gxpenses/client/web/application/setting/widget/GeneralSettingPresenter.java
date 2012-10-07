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
import com.nuvola.gxpenses.shared.domaine.User;
import com.nuvola.gxpenses.shared.dto.ValidatedResponse;
import org.fusesource.restygwt.client.Method;

public class GeneralSettingPresenter extends PresenterWidget<GeneralSettingPresenter.MyView>
        implements GeneralSettingUiHandlers {

    public interface MyView extends View, HasUiHandlers<GeneralSettingUiHandlers> {
        void edit(User user);
    }

    private final SettingService settingService;
    private final MessageBundle messageBundle;

    private User currentUser;

    @Inject
    public GeneralSettingPresenter(EventBus eventBus, MyView view, final SettingService settingService,
                                   final MessageBundle messageBundle) {
        super(eventBus, view);

        this.settingService = settingService;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveSetting(User editedUser) {
        settingService.updateUserSettings(editedUser, new MethodCallbackImpl<ValidatedResponse<User>>() {
            @Override
            public void onSuccess(Method method, ValidatedResponse<User> userValidatedResponse) {
                GlobalMessageEvent.fire(this, messageBundle.settingsUpdated());
            }
        });
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        settingService.getLoggedInUserSettings(new MethodCallbackImpl<User>() {
            @Override
            public void onSuccess(Method method, User user) {
                currentUser = user;
                getView().edit(currentUser);
            }
        });
    }

}
