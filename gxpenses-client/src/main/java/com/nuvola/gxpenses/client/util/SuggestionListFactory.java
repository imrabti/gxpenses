package com.nuvola.gxpenses.client.util;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.nuvola.gxpenses.client.rest.UserService;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuggestionListFactory {
    private final RestDispatchAsync dispatcher;
    private final UserService userService;

    private List<String> listTags;
    private List<String> listPayee;

    @Inject
    SuggestionListFactory(RestDispatchAsync dispatcher,
                          UserService userService) {
        this.dispatcher = dispatcher;
        this.userService = userService;
    }

    public List<String> getListTags() {
        if (listTags == null) {
            dispatcher.execute(userService.tag().findAllTags(), new AsyncCallbackImpl<List<String>>() {
                @Override
                public void onReceive(List<String> response) {
                    listTags = response;
                }
            });
        }

        return listTags;
    }

    public List<String> getListPayee() {
        if (listPayee == null) {
            dispatcher.execute(userService.payee().findAllPayee(), new AsyncCallbackImpl<List<String>>() {
                @Override
                public void onReceive(List<String> response) {
                    listPayee = response;
                }
            });
        }

        return listPayee;
    }

    public void updatePayeeList(String payee) {
        if (!listPayee.contains(payee)) {
            listPayee.add(payee);
        }
    }

    public void updateTagsList(String tag) {
        if (!Strings.isNullOrEmpty(tag)) {
            List<String> tags = Arrays.asList(tag.split(","));
            List<String> toAdd = new ArrayList<String>();

            for (String item : tags) {
                if (!listTags.contains(item.trim().toLowerCase())) {
                    listTags.add(item);
                    toAdd.add(item);
                }
            }

            dispatcher.execute(userService.tag().createTags(toAdd), new AsyncCallbackImpl<Void>() {
                @Override
                public void onReceive(Void response) {
                }
            });
        }
    }

    public void reloadTagsList() {
        listTags.clear();
        listTags = null;
        getListTags();
    }
}
