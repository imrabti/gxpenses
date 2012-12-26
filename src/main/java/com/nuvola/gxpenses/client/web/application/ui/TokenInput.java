package com.nuvola.gxpenses.client.web.application.ui;

import com.google.common.base.Strings;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;

import java.util.List;

public class TokenInput extends Composite {
    public interface Binder extends UiBinder<Widget, TokenInput> {
    }

    @UiField(provided = true)
    CellList<List<String>> tokenList;
    @UiField
    SuggestBox itemBox;

    private final ListDataProvider<List<String>> dataProvider;

    @Inject
    public TokenInput(final Binder uiBinder) {

        dataProvider = new ListDataProvider<List<String>>();

        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("itemBox")
    void onItemBoxKeyDown(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            deselectItem(itemBox, list);
        }

        if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
            if (Strings.isNullOrEmpty(itemBox.getText())) {
                dataProvider.getList().get()
            }

            if ("".equals(itemBox.getValue().trim())) {
                ListItem li = (ListItem) list.getWidget(list.getWidgetCount() - 2);
                Paragraph p = (Paragraph) li.getWidget(0);
                if (itemsSelected.contains(p.getText())) {
                    itemsSelected.remove(p.getText());
                    GWT.log("Removing selected item '" + p.getText() + "'", null);
                    GWT.log("Remaining: " + itemsSelected, null);
                }
                list.remove(li);
                itemBox.setFocus(true);
            }
        }
    }
}
