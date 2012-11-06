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
import com.nuvola.gxpenses.client.rest.UserService;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;

import java.util.List;

public class TagSettingPresenter extends PresenterWidget<TagSettingPresenter.MyView> implements TagSettingUiHandlers {

    public interface MyView extends View, HasUiHandlers<TagSettingUiHandlers> {
        void setData(List<String> tags);
    }

    private final SettingService settingService;
    private final UserService userService;
    private final SuggestionListFactory suggestionListFactory;
    private final MessageBundle messageBundle;

    @Inject
    public TagSettingPresenter(EventBus eventBus, MyView view, final SettingService settingService,
                               final UserService userService, final MessageBundle messageBundle,
                               final SuggestionListFactory suggestionListFactory) {
        super(eventBus, view);

        this.settingService = settingService;
        this.userService = userService;
        this.suggestionListFactory = suggestionListFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveTags(List<String> tags) {
        settingService.updateUserTags(tags, new MethodCallbackImpl<Void>() {
            @Override
            public void handleSuccess(Void aVoid) {
                suggestionListFactory.reloadTagsList();
                GlobalMessageEvent.fire(this, messageBundle.tagsUpdated());
            }
        });
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        userService.getTags(new MethodCallbackImpl<List<String>>() {
            @Override
            public void handleSuccess(List<String> tags) {
                getView().setData(tags);
            }
        });
    }

}
