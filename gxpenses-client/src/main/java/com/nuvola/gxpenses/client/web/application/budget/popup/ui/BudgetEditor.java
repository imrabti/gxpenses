package com.nuvola.gxpenses.client.web.application.budget.popup.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.web.application.renderer.EnumRenderer;
import com.nuvola.gxpenses.common.client.util.EditorView;
import com.nuvola.gxpenses.common.shared.business.Budget;
import com.nuvola.gxpenses.common.shared.type.FrequencyType;

import java.util.Arrays;

public class BudgetEditor extends Composite implements EditorView<Budget> {
    public interface Binder extends UiBinder<HTMLPanel, BudgetEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<Budget, BudgetEditor> {
    }

    @UiField
    TextBox name;
    @UiField(provided=true)
    ValueListBox<FrequencyType> periodicity;

    private final Driver driver;

    @Inject
    public BudgetEditor(final Binder uiBinder, final Driver driver) {
        this.driver = driver;

        //Initialize ValusListBox elements
        periodicity = new ValueListBox<FrequencyType>(new EnumRenderer<FrequencyType>());
        periodicity.setAcceptableValues(Arrays.asList(FrequencyType.values()));
        periodicity.setValue(FrequencyType.MONTH);

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    public void edit(Budget budget) {
        name.setFocus(true);
        driver.edit(budget);
    }

    public Budget get() {
        Budget budget = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return budget;
        }
    }
}
