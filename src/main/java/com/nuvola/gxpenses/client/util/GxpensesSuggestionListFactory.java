package com.nuvola.gxpenses.client.util;

import java.util.ArrayList;
import java.util.List;

import com.nuvola.gxpenses.client.rest.MethodCallBackImpl;
import com.nuvola.gxpenses.client.rest.UserService;

import com.google.inject.Inject;
import org.fusesource.restygwt.client.Method;

public class GxpensesSuggestionListFactory {

    private final UserService userService;
	
	private List<String> listTags;
	private List<String> listPayee;
	
	@Inject
	public GxpensesSuggestionListFactory(final UserService userService) {
		this.userService = userService;
	}

	public List<String> getListTags() {
		if (listTags == null) {
            userService.getTags(new MethodCallBackImpl<List<String>>() {
                @Override
                public void onSuccess(Method method, List<String> tags) {
                    listTags = tags;
                }
            });
		}
		
		return listTags;
	}

	public List<String> getListPayee() {
		if (listPayee == null) {
            userService.getPayees(new MethodCallBackImpl<List<String>>() {
                @Override
                public void onSuccess(Method method, List<String> payees) {
                    listPayee = payees;
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

    public void updateTagsList(List<String> tags) {
        List<String> toAdd = new ArrayList<String>();
        for(String tag : tags) {
            if(!listTags.contains(tag.trim().toLowerCase())) {
                listTags.add(tag);
                toAdd.add(tag);
            }
        }

        userService.updateTags(toAdd, new MethodCallBackImpl<Void>() {
            @Override
            public void onSuccess(Method method, Void aVoid) {
            }
        });
    }

}
