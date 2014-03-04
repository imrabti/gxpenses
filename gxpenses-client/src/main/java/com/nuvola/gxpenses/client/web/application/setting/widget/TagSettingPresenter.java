package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.request.ReceiverImpl;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;

import java.util.List;

public class TagSettingPresenter extends PresenterWidget<TagSettingPresenter.MyView> implements TagSettingUiHandlers {
    public interface MyView extends View, HasUiHandlers<TagSettingUiHandlers> {
        void setData(List<String> tags);
    }

    private final GxpensesRequestFactory requestFactory;
    private final SuggestionListFactory suggestionListFactory;
    private final MessageBundle messageBundle;

    @Inject
    public TagSettingPresenter(EventBus eventBus, MyView view, final GxpensesRequestFactory requestFactory,
                               final MessageBundle messageBundle, final SuggestionListFactory suggestionListFactory) {
        super(eventBus, view);

        this.requestFactory = requestFactory;
        this.suggestionListFactory = suggestionListFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveTags(List<String> tags) {
        requestFactory.userService().updateTags(tags).fire(new ReceiverImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                suggestionListFactory.reloadTagsList();
                GlobalMessageEvent.fire(this, messageBundle.tagsUpdated());
            }
        });
    }

    @Override
    protected void onReveal() {
        requestFactory.userService().findAllTagsForUser().fire(new ReceiverImpl<List<String>>() {
            @Override
            public void onSuccess(List<String> tags) {
                getView().setData(tags);
            }
        });
    }
}
