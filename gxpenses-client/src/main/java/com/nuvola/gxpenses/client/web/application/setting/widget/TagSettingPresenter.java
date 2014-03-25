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
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;

import java.util.List;

public class TagSettingPresenter extends PresenterWidget<TagSettingPresenter.MyView> implements TagSettingUiHandlers {
    public interface MyView extends View, HasUiHandlers<TagSettingUiHandlers> {
        void setData(List<String> tags);
    }

    private final RestDispatchAsync dispatcher;
    private final UserService userService;
    private final SuggestionListFactory suggestionListFactory;
    private final MessageBundle messageBundle;

    @Inject
    TagSettingPresenter(EventBus eventBus,
                        MyView view,
                        RestDispatchAsync dispatcher,
                        UserService userService,
                        MessageBundle messageBundle,
                        SuggestionListFactory suggestionListFactory) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.userService = userService;
        this.suggestionListFactory = suggestionListFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveTags(List<String> tags) {
        dispatcher.execute(userService.tag().createTags(tags), new AsyncCallbackImpl<Void>() {
            @Override
            public void onReceive(Void response) {
                suggestionListFactory.reloadTagsList();
                GlobalMessageEvent.fire(this, messageBundle.tagsUpdated());
            }
        });
    }

    @Override
    protected void onReveal() {
        dispatcher.execute(userService.tag().findAllTags(), new AsyncCallbackImpl<List<String>>() {
            @Override
            public void onReceive(List<String> response) {
                getView().setData(response);
            }
        });
    }
}
