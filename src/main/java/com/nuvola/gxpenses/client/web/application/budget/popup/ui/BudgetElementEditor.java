package com.nuvola.gxpenses.client.web.application.budget.popup.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestBox.DefaultSuggestionDisplay;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.request.proxy.BudgetElementProxy;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;

public class BudgetElementEditor extends Composite implements EditorView<BudgetElementProxy> {
    public interface Binder extends UiBinder<HTMLPanel, BudgetElementEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<BudgetElementProxy, BudgetElementEditor> {
    }

    public interface Handler {
        void onBudgetElementAdded(BudgetElementProxy budgetElement);
    }

    @UiField(provided=true)
    SuggestBox tag;
    @UiField
    DoubleBox amount;

    private final Driver driver;
    private final SuggestionListFactory suggestionListFactory;
    private Handler handler;

    @Inject
    public BudgetElementEditor(final Binder uiBinder, final Driver driver,
                               final SuggestionListFactory suggestionListFactory,
                               final Resources resources) {
        this.driver = driver;
        this.suggestionListFactory = suggestionListFactory;
        this.tag = new SuggestBox(new MultiWordSuggestOracle());

        DefaultSuggestionDisplay tagSuggestionDisplay = (DefaultSuggestionDisplay) tag.getSuggestionDisplay();
        tagSuggestionDisplay.setPopupStyleName(resources.suggestBoxStyleCss().gwtSuggestBoxPoup());

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    @Override
    public void edit(BudgetElementProxy budgetElement) {
        initSuggestionList();
        tag.setFocus(true);
        driver.edit(budgetElement);
    }

    @Override
    public BudgetElementProxy get() {
        BudgetElementProxy budgetElement = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return budgetElement;
        }
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @UiHandler("addElement")
    void onAddElementClicked(ClickEvent event) {
        BudgetElementProxy budgetElement = get();
        if (budgetElement != null) {
            handler.onBudgetElementAdded(budgetElement);
        }
    }

    @UiHandler("amount")
    void onAmountKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            BudgetElementProxy budgetElement = get();
            if (budgetElement != null) {
                handler.onBudgetElementAdded(budgetElement);
            }
        }
    }

    private void initSuggestionList() {
        ((MultiWordSuggestOracle) tag.getSuggestOracle()).clear();

        if (suggestionListFactory.getListTags() != null && !suggestionListFactory.getListTags().isEmpty()) {
            ((MultiWordSuggestOracle) tag.getSuggestOracle()).addAll(suggestionListFactory.getListTags());
        }
    }
}
