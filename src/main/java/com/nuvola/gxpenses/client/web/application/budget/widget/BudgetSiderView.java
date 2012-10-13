package com.nuvola.gxpenses.client.web.application.budget.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.resource.SiderListStyle;
import com.nuvola.gxpenses.client.web.application.budget.renderer.BudgetCell;
import com.nuvola.gxpenses.shared.domaine.Budget;

import java.util.List;

public class BudgetSiderView extends ViewWithUiHandlers<BudgetSiderUiHandlers> implements BudgetSiderPresenter.MyView {

    public interface Binder extends UiBinder<Widget, BudgetSiderView> {
    }

    @UiField(provided=true)
    CellList<Budget> budgetList;
    @UiField
    Button addNew;

    private final ProvidesKey<Budget> keyProvider;
    private final ListDataProvider<Budget> dataProvider;
    private final SingleSelectionModel<Budget> selectionModel;

    @Inject
    public BudgetSiderView(final Binder uiBinder,
                           final UiHandlersStrategy<BudgetSiderUiHandlers> uiHandlers,
                           final BudgetCell budgetCell, final Resources resources,
                           final SiderListStyle siderListResources) {
        super(uiHandlers);

        this.keyProvider = setupKeyProvider();
        this.dataProvider = new ListDataProvider<Budget>(keyProvider);

        budgetList = new CellList<Budget>(budgetCell, siderListResources);
        selectionModel = new SingleSelectionModel<Budget>(keyProvider);

        //Init The UI Binder
        initWidget(uiBinder.createAndBindUi(this));

        //Set up CSS Style Classes
        addNew.setStylePrimaryName(resources.buttonStyleCss().button());
        addNew.setStyleName(resources.buttonStyleCss().medium(), true);
        addNew.setStyleName(resources.buttonStyleCss().gray(), true);

        budgetList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);
        budgetList.setSelectionModel(selectionModel);
        dataProvider.addDataDisplay(budgetList);

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                getUiHandlers().budgetSelected(selectionModel.getSelectedObject());
            }
        });
    }

    @Override
    public void setData(List<Budget> budgets) {
        dataProvider.getList().clear();
        dataProvider.getList().addAll(budgets);
        dataProvider.refresh();
    }

    @UiHandler("addNew")
    void onAddAccount(ClickEvent event) {
        getUiHandlers().addNewBudget((Widget) event.getSource());
    }

    private ProvidesKey<Budget> setupKeyProvider() {
        return new ProvidesKey<Budget>() {
            @Override
            public Object getKey(Budget budget) {
                return budget == null ? null : budget.getId();
            }
        };
    }

}
