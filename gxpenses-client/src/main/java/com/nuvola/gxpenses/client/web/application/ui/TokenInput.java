package com.nuvola.gxpenses.client.web.application.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Strings;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestBox.DefaultSuggestionDisplay;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.resource.style.list.TokenListStyle;
import com.nuvola.gxpenses.client.web.application.renderer.TokenCellFactory;

public class TokenInput extends Composite implements LeafValueEditor<String> {
    public interface Binder extends UiBinder<Widget, TokenInput> {
    }

    @UiField(provided = true)
    CellList<String> tokenList;
    @UiField(provided = true)
    SuggestBox itemBox;

    private final ListDataProvider<String> dataProvider;

    @Inject
    TokenInput(Binder uiBinder,
               TokenListStyle tokenListStyle,
               TokenCellFactory tokenCellFactory,
               Resources resources) {

        dataProvider = new ListDataProvider<String>();
        itemBox = new SuggestBox(new MultiWordSuggestOracle());
        tokenList = new CellList<String>(tokenCellFactory.create(setupRemoveAction()), tokenListStyle);

        initWidget(uiBinder.createAndBindUi(this));
        dataProvider.addDataDisplay(tokenList);

        DefaultSuggestionDisplay tagSuggestionDisplay = (DefaultSuggestionDisplay) itemBox.getSuggestionDisplay();
        tagSuggestionDisplay.setPopupStyleName(resources.suggestBoxStyleCss().gwtSuggestBoxPoup());

        itemBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            public void onSelection(SelectionEvent selectionEvent) {
                String value = itemBox.getValue().toLowerCase().trim();
                if (!dataProvider.getList().contains(value)) {
                    dataProvider.getList().add(value);
                    dataProvider.refresh();
                }

                itemBox.setText("");
            }
        });

        itemBox.getValueBox().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                onItemBoxKeyUp(event);
            }
        });
    }

    @Override
    public void setValue(String value) {
        List<String> tags = new ArrayList<String>();
        if (!Strings.isNullOrEmpty(value)) {
            tags = Arrays.asList(value.split(","));
        }

        dataProvider.getList().clear();
        dataProvider.getList().addAll(tags);
        dataProvider.refresh();
    }

    @Override
    public String getValue() {
        StringBuilder builder = new StringBuilder();
        for (String tag : dataProvider.getList()) {
            Integer index = dataProvider.getList().indexOf(tag);
            if (index == dataProvider.getList().size() - 1) {
                builder.append(tag);
            } else {
                builder.append(tag + ", ");
            }
        }

        return builder.toString();
    }

    public void addSuggestion(List<String> tags) {
        ((MultiWordSuggestOracle) itemBox.getSuggestOracle()).addAll(tags);
    }

    public void clearSuggestion() {
        ((MultiWordSuggestOracle) itemBox.getSuggestOracle()).clear();
    }

    private void onItemBoxKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            if (!Strings.isNullOrEmpty(itemBox.getText())) {
                String value = itemBox.getText().toLowerCase().trim();
                if (!dataProvider.getList().contains(value)) {
                    dataProvider.getList().add(value);
                    dataProvider.refresh();
                }

                itemBox.setText("");
            }
        }

        if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
            if (Strings.isNullOrEmpty(itemBox.getText())) {
                Integer lastElement = dataProvider.getList().size() - 1;
                if (lastElement >= 0) {
                    String value = dataProvider.getList().get(lastElement);
                    dataProvider.getList().remove(value);
                    dataProvider.refresh();
                }
            }
        }
    }

    private ActionCell.Delegate<String> setupRemoveAction() {
        return new ActionCell.Delegate<String>() {
            @Override
            public void execute(String tag) {
                dataProvider.getList().remove(tag);
                dataProvider.refresh();
            }
        };
    }
}
