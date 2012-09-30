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
import com.nuvola.gxpenses.client.resource.GxpensesRes;
import com.nuvola.gxpenses.client.util.EditorView;
import com.nuvola.gxpenses.client.web.application.renderer.EnumRenderer;
import com.nuvola.gxpenses.client.web.application.ui.MultipleSuggestBox;
import com.nuvola.gxpenses.shared.domaine.Transaction;
import com.nuvola.gxpenses.shared.type.TransactionType;

import java.util.Arrays;

public class TransactionEditor extends Composite implements EditorView<Transaction> {

    public interface Binder extends UiBinder<HTMLPanel, TransactionEditor> {
    }

    public interface TransactionDriver extends SimpleBeanEditorDriver<Transaction, TransactionEditor> {
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

    private final TransactionDriver driver;

    @Inject
    public TransactionEditor(final Binder uiBinder, final TransactionDriver driver,
                             final GxpensesRes resources) {
        this.driver = driver;

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

    public void edit(Transaction transaction) {
        payee.setFocus(true);
        driver.edit(transaction);
    }

    public Transaction get() {
        Transaction transaction = driver.flush();
        if(driver.hasErrors()) {
            return null;
        } else {
            return transaction;
        }
    }

}
