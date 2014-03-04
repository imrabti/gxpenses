package com.nuvola.gxpenses.client.web.application.budget;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.nuvola.gxpenses.client.event.NoElementFoundEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.event.SetVisibleSiderEvent;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.BudgetService;
import com.nuvola.gxpenses.client.security.LoggedInGatekeeper;
import com.nuvola.gxpenses.client.util.DateUtils;
import com.nuvola.gxpenses.client.web.application.ApplicationPresenter;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetElementPresenter;
import com.nuvola.gxpenses.client.web.application.budget.widget.BudgetSiderPresenter;
import com.nuvola.gxpenses.common.client.rest.AsyncCallbackImpl;
import com.nuvola.gxpenses.common.client.util.EmptyDisplay;
import com.nuvola.gxpenses.common.shared.business.Budget;
import com.nuvola.gxpenses.common.shared.business.BudgetElement;
import com.nuvola.gxpenses.common.shared.dto.BudgetProgressTotal;

import java.util.Date;
import java.util.List;

public class BudgetPresenter extends Presenter<BudgetPresenter.MyView, BudgetPresenter.MyProxy>
        implements BudgetUiHandlers, NoElementFoundEvent.NoElementFoundHandler,
        BudgetChangedEvent.BudgetChangedHandler, PopupClosedEvent.PopupClosedHandler,
        BudgetElementsChangedEvent.BudgetElementsChangedHandler {
    public interface MyView extends View, EmptyDisplay, HasUiHandlers<BudgetUiHandlers> {
        void setData(List<BudgetElement> data, BudgetProgressTotal total);

        void setPeriod(String periodName);

        void showElementsPanel();

        void hideElementsPanel();

        void showNoBudgetsPanel();

        void hideNoBudgetsPanel();

        void switchBudgetSettingsStyle();

        Boolean isEmptyVisible();
    }

    @ProxyStandard
    @NameToken(NameTokens.budget)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<BudgetPresenter> {
    }

    private final RestDispatchAsync dispatcher;
    private final BudgetService budgetService;
    private final MessageBundle messageBundle;
    private final BudgetSiderPresenter budgetSiderPresenter;
    private final AddBudgetElementPresenter addBudgetElementPresenter;

    private Budget currentBudget;
    private Date currentDate;

    @Inject
    BudgetPresenter(EventBus eventBus,
                    MyView view,
                    MyProxy proxy,
                    RestDispatchAsync dispatcher,
                    BudgetService budgetService,
                    MessageBundle messageBundle,
                    BudgetSiderPresenter budgetSiderPresenter,
                    AddBudgetElementPresenter addBudgetElementPresenter) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.budgetService = budgetService;
        this.messageBundle = messageBundle;
        this.budgetSiderPresenter = budgetSiderPresenter;
        this.addBudgetElementPresenter = addBudgetElementPresenter;

        getView().setUiHandlers(this);
    }

    @Override
    public void elementSetting(Widget relativeTo) {
        addBudgetElementPresenter.setRelativeTo(relativeTo);
        addBudgetElementPresenter.setSelectedBudget(currentBudget);
        addToPopupSlot(addBudgetElementPresenter, false);
    }

    @Override
    public void nextPeriod() {
        currentDate = DateUtils.getNextDate(currentDate, currentBudget.getPeriodicity());
        getView().setPeriod(DateUtils.getDateToDisplay(currentDate, currentBudget.getPeriodicity()));

        fireLoadBudgetElementByIdAndPeriod();
    }

    @Override
    public void previousPeriod() {
        currentDate = DateUtils.getPreviousDate(currentDate, currentBudget.getPeriodicity());
        getView().setPeriod(DateUtils.getDateToDisplay(currentDate, currentBudget.getPeriodicity()));

        fireLoadBudgetElementByIdAndPeriod();
    }

    @Override
    public void onNoElementFound(NoElementFoundEvent event) {
        if (event.getSize() == 0) {
            if (!getView().isEmptyVisible()) {
                getView().hideElementsPanel();
            }
            getView().setEmptyMessage(messageBundle.noBudgets());
        } else {
            getView().setEmptyMessage(messageBundle.noSelectedBudget());
        }
    }

    @Override
    public void onBudgetChanged(BudgetChangedEvent event) {
        if (event.getBudget() == null) {
            currentDate = new Date();
            currentBudget = null;

            if (!getView().isEmptyVisible()) {
                getView().hideElementsPanel();
            }
        } else {
            currentDate = new Date();
            currentBudget = event.getBudget();

            getView().setPeriod(DateUtils.getDateToDisplay(currentDate, currentBudget.getPeriodicity()));
            if (getView().isEmptyVisible()) {
                getView().showElementsPanel();
            }

            fireLoadBudgetElementByIdAndPeriod();
        }
    }

    @Override
    public void onPopupClosed(PopupClosedEvent event) {
        getView().switchBudgetSettingsStyle();
    }

    @Override
    public void onBudgetElementsChanged(BudgetElementsChangedEvent event) {
        if (currentBudget != null) {
            fireLoadBudgetElementByIdAndPeriod();
        }
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(NoElementFoundEvent.getType(), this);
        addRegisteredHandler(BudgetChangedEvent.getType(), this);
        addRegisteredHandler(PopupClosedEvent.getType(), this);
        addRegisteredHandler(BudgetElementsChangedEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        SetVisibleSiderEvent.fire(this, budgetSiderPresenter);
    }

    private void fireLoadBudgetElementByIdAndPeriod() {
        dispatcher.execute(budgetService.budgetElement(currentBudget.getId()).findAllBudgetElements(currentDate),
                new AsyncCallbackImpl<List<BudgetElement>>() {
            @Override
            public void onReceive(List<BudgetElement> response) {
                getView().setData(response, new BudgetProgressTotal(currentBudget.getTotalAllowed(),
                        currentBudget.getTotalConsumed()));

                if (response.size() > 0) {
                    getView().hideNoBudgetsPanel();
                } else {
                    getView().showNoBudgetsPanel();
                }
            }
        });
    }
}
