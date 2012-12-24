package com.nuvola.gxpenses.client.web.application.transaction.popup.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestBox.DefaultSuggestionDisplay;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.request.proxy.TransactionProxy;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.util.SuggestionListFactory;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.application.renderer.EnumRenderer;
import com.nuvola.gxpenses.client.web.application.ui.MultipleSuggestBox;
import com.nuvola.gxpenses.shared.type.TransactionType;

import java.util.Arrays;

public class TransactionEditor extends Composite implements EditorView<TransactionProxy> {

    public interface Binder extends UiBinder<HTMLPanel, TransactionEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<TransactionProxy, TransactionEditor> {
    }

    @UiField(provided = true)
    SuggestBox payee;
    @UiField(provided = true)
    ValueListBox<TransactionType> type;
    @UiField(provided = true)
    MultipleSuggestBox tags;
    @UiField
    DateBox date;
    @UiField
    DoubleBox amount;

    private final Driver driver;
    private final SuggestionListFactory suggestionListFactory;

    @Inject
    public TransactionEditor(final Binder uiBinder, final Driver driver,
                             final SuggestionListFactory suggestionListFactory,
                             final Resources resources) {
        this.driver = driver;
        this.suggestionListFactory = suggestionListFactory;

        // Initialize ValusListBox elements
        payee = new SuggestBox(new MultiWordSuggestOracle());
        tags = new MultipleSuggestBox(new MultiWordSuggestOracle(","));
        type = new ValueListBox<TransactionType>(new EnumRenderer<TransactionType>());
        type.setValue(TransactionType.EXPENSE);
        type.setAcceptableValues(Arrays.asList(new TransactionType[]{TransactionType.EXPENSE, TransactionType.INCOME}));

        initWidget(uiBinder.createAndBindUi(this));

        // Set up CSS Style Classes
        DefaultSuggestionDisplay tagSuggestionDisplay = (DefaultSuggestionDisplay) tags.getSuggestionDisplay();
        tagSuggestionDisplay.setPopupStyleName(resources.suggestBoxStyleCss().gwtSuggestBoxPoup());
        DefaultSuggestionDisplay payeeSuggestionDisplay = (DefaultSuggestionDisplay) payee.getSuggestionDisplay();
        payeeSuggestionDisplay.setPopupStyleName(resources.suggestBoxStyleCss().gwtSuggestBoxPoup());
        date.getDatePicker().setStyleName(resources.datePickerStyle().gwtDatePicker());
        date.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT)));

        driver.initialize(this);
    }

    @Override
    public void edit(TransactionProxy transaction) {
        initSuggestionList();
        payee.setFocus(true);
        driver.edit(transaction);
    }

    @Override
    public TransactionProxy get() {
        TransactionProxy transaction = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            int multiplier = transaction.getType() == TransactionType.EXPENSE ? -1 : 1;
            transaction.setAmount(transaction.getAmount() * multiplier);
            return transaction;
        }
    }

    private void initSuggestionList() {
        ((MultiWordSuggestOracle) payee.getSuggestOracle()).clear();
        ((MultiWordSuggestOracle) tags.getSuggestOracle()).clear();

        if (suggestionListFactory.getListPayee() != null && !suggestionListFactory.getListPayee().isEmpty()) {
            ((MultiWordSuggestOracle) payee.getSuggestOracle()).addAll(suggestionListFactory.getListPayee());
        }
        if (suggestionListFactory.getListTags() != null && !suggestionListFactory.getListTags().isEmpty()) {
            ((MultiWordSuggestOracle) tags.getSuggestOracle()).addAll(suggestionListFactory.getListTags());
        }
    }
}
