package com.nuvola.gxpenses.client.web.application.budget.popup.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.shared.domaine.BudgetElement;

public class BudgetElementEditor extends Composite implements EditorView<BudgetElement> {

    public interface Binder extends UiBinder<HTMLPanel, BudgetElementEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<BudgetElement, BudgetElementEditor> {
    }

    @UiField(provided=true)
    SuggestBox tag;
    @UiField
    DoubleBox amount;

    private final Driver driver;
    private final SuggestionListFactory suggestionListFactory;

    @Inject
    public BudgetElementEditor(final Binder uiBinder, final Driver driver,
                               final SuggestionListFactory suggestionListFactory) {
        this.driver = driver;
        this.suggestionListFactory = suggestionListFactory;
        this.tag = new SuggestBox(new MultiWordSuggestOracle());

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    @Override
    public void edit(BudgetElement budgetElement) {
        initSuggestionList();
        tag.setFocus(true);
        driver.edit(budgetElement);
    }

    @Override
    public BudgetElement get() {
        BudgetElement budgetElement = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return budgetElement;
        }
    }

    @UiHandler("addElement")
    void onAddElementClicked(ClickEvent event) {

    }

    private void initSuggestionList() {
        ((MultiWordSuggestOracle) tag.getSuggestOracle()).clear();

        if (suggestionListFactory.getListTags() != null && !suggestionListFactory.getListTags().isEmpty()) {
            ((MultiWordSuggestOracle) tag.getSuggestOracle()).addAll(suggestionListFactory.getListTags());
        }
    }

}
