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
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.request.proxy.BudgetProxy;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.resource.style.list.SiderListStyle;
import com.nuvola.gxpenses.client.web.application.budget.renderer.BudgetCell;

import java.util.List;

public class BudgetSiderView extends ViewWithUiHandlers<BudgetSiderUiHandlers> implements BudgetSiderPresenter.MyView {
    public interface Binder extends UiBinder<Widget, BudgetSiderView> {
    }

    @UiField(provided=true)
    CellList<BudgetProxy> budgetList;
    @UiField
    Button addNew;

    private final ProvidesKey<BudgetProxy> keyProvider;
    private final ListDataProvider<BudgetProxy> dataProvider;
    private final SingleSelectionModel<BudgetProxy> selectionModel;

    @Inject
    public BudgetSiderView(final Binder uiBinder,
                           final BudgetCell budgetCell, final Resources resources,
                           final SiderListStyle siderListResources) {
        this.keyProvider = setupKeyProvider();
        this.dataProvider = new ListDataProvider<BudgetProxy>(keyProvider);

        budgetList = new CellList<BudgetProxy>(budgetCell, siderListResources);
        selectionModel = new SingleSelectionModel<BudgetProxy>(keyProvider);

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
    public void setData(List<BudgetProxy> budgets) {
        dataProvider.getList().clear();
        dataProvider.getList().addAll(budgets);
        dataProvider.refresh();
    }

    @Override
    public void clearSelection() {
        selectionModel.clear();
    }

    @UiHandler("addNew")
    void onAddAccount(ClickEvent event) {
        getUiHandlers().addNewBudget((Widget) event.getSource());
    }

    private ProvidesKey<BudgetProxy> setupKeyProvider() {
        return new ProvidesKey<BudgetProxy>() {
            @Override
            public Object getKey(BudgetProxy budget) {
                return budget == null ? null : budget.getId();
            }
        };
    }
}
