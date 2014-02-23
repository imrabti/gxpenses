package com.nuvola.gxpenses.client.web.application.budget.popup;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.nuvola.gxpenses.client.gin.Currency;
import com.nuvola.gxpenses.client.mvp.PopupViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.request.proxy.BudgetElementProxy;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.resource.style.table.SmallTableStyle;
import com.nuvola.gxpenses.client.web.application.budget.popup.ui.BudgetElementEditor;
import com.nuvola.gxpenses.client.web.application.budget.renderer.RemoveBudgetElementCellFactory;
import com.nuvola.gxpenses.client.web.application.renderer.ClickableIconCell;

import java.util.List;

public class AddBudgetElementView extends PopupViewWithUiHandlers<AddBudgetElementUiHandlers>
        implements AddBudgetElementPresenter.MyView, BudgetElementEditor.Handler {
    public interface Binder extends UiBinder<PopupPanel, AddBudgetElementView> {
    }

    @UiField
    PopupPanel popup;
    @UiField(provided = true)
    CellTable<BudgetElementProxy> elementsTable;
    @UiField(provided = true)
    BudgetElementEditor budgetElementEditor;

    private final ProvidesKey<BudgetElementProxy> keyProvider;
    private final ListDataProvider<BudgetElementProxy> dataProvider;
    private final RemoveBudgetElementCellFactory removeBudgetElementCellFactory;
    private final Resources resources;
    private final ActionCell.Delegate<BudgetElementProxy> removeDelegate;
    private final String currency;

    @Inject
    public AddBudgetElementView(final EventBus eventBus, final Binder uiBinder,
                                final UiHandlersStrategy<AddBudgetElementUiHandlers> uiHandlersStrategy,
                                final SmallTableStyle smallTableStyle, final BudgetElementEditor budgetElementEditor,
                                final RemoveBudgetElementCellFactory removeBudgetElementCellFactory,
                                final Resources resources, @Currency String currency) {
        super(eventBus, uiHandlersStrategy);

        this.currency = currency;
        this.resources = resources;
        this.budgetElementEditor = budgetElementEditor;
        this.keyProvider = setupKeyProvider();
        this.removeDelegate = setupRemoveAction();
        this.removeBudgetElementCellFactory = removeBudgetElementCellFactory;
        this.dataProvider = new ListDataProvider<BudgetElementProxy>(keyProvider);

        elementsTable = new CellTable<BudgetElementProxy>(20, smallTableStyle);
        elementsTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);
        dataProvider.addDataDisplay(elementsTable);

        initWidget(uiBinder.createAndBindUi(this));
        budgetElementEditor.setHandler(this);
        initCellTable();
    }

    @Override
    public void showRelativeTo(Widget widget) {
        int left = widget.getAbsoluteLeft() - (popup.getOffsetWidth() - widget.getOffsetWidth());
        int top = widget.getAbsoluteTop() + widget.getOffsetHeight();
        setPosition(left, top);
    }

    @Override
    public void edit(BudgetElementProxy budgetElement) {
        budgetElementEditor.edit(budgetElement);
    }

    @Override
    public void setData(List<BudgetElementProxy> budgetElements) {
        dataProvider.getList().clear();
        dataProvider.getList().addAll(budgetElements);
        dataProvider.refresh();
    }

    @Override
    public void onBudgetElementAdded(BudgetElementProxy budgetElement) {
        getUiHandlers().addNewBudgetElement(budgetElement);
    }

    @UiHandler("popup")
    void onClose(CloseEvent<PopupPanel> closeEvent) {
        getUiHandlers().close();
    }

    @UiHandler("close")
    void onCloseClicked(ClickEvent event) {
        getUiHandlers().close();
        hide();
    }

    private ProvidesKey<BudgetElementProxy> setupKeyProvider() {
        return new ProvidesKey<BudgetElementProxy>() {
            @Override
            public Object getKey(BudgetElementProxy budgetElement) {
                return budgetElement == null ? null : budgetElement.getId();
            }
        };
    }

    private ActionCell.Delegate<BudgetElementProxy> setupRemoveAction() {
        return new ActionCell.Delegate<BudgetElementProxy>() {
            @Override
            public void execute(BudgetElementProxy budgetElement) {
                getUiHandlers().removeBudgetElement(budgetElement);
            }
        };
    }

    private void initCellTable() {
        setupTagColumn();
        setupBudgetAmountColumn();
        setupRemoveActionColumn();
    }

    private void setupTagColumn() {
        TextColumn<BudgetElementProxy> tagColumn = new TextColumn<BudgetElementProxy>() {
            @Override
            public String getValue(BudgetElementProxy object) {
                return object.getTag();
            }
        };
        elementsTable.addColumn(tagColumn, "Tag");
        elementsTable.setColumnWidth(tagColumn, 150, Style.Unit.PX);
    }

    private void setupBudgetAmountColumn() {
        NumberCell numberCell = new NumberCell(NumberFormat.getFormat("###,##0.00"));
        Column<BudgetElementProxy, Number> amountColumn = new Column<BudgetElementProxy, Number>(numberCell) {
            @Override
            public Number getValue(BudgetElementProxy object) {
                return object.getAmount();
            }
        };
        amountColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        elementsTable.addColumn(amountColumn, "Amount" + " ("+ currency + ")");
        elementsTable.setColumnWidth(amountColumn, 100, Style.Unit.PX);
    }

    private void setupRemoveActionColumn() {
        ClickableIconCell<BudgetElementProxy> removeCell = removeBudgetElementCellFactory.create(resources.removeIcon(),
                removeDelegate);
        Column<BudgetElementProxy, BudgetElementProxy> removeColumn =
                new Column<BudgetElementProxy, BudgetElementProxy>(removeCell) {
            @Override
            public BudgetElementProxy getValue(BudgetElementProxy object) {
                return object;
            }
        };
        removeColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        elementsTable.addColumn(removeColumn, "");
        elementsTable.setColumnWidth(removeColumn, 30, Style.Unit.PX);
    }
}
