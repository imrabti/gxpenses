package com.nuvola.gxpenses.client.web.application.budget;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.nuvola.gxpenses.client.event.NoElementFoundEvent;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.event.SetVisibleSiderEvent;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.BudgetElementService;
import com.nuvola.gxpenses.client.rest.MethodCallbackImpl;
import com.nuvola.gxpenses.client.security.LoggedInGatekeeper;
import com.nuvola.gxpenses.client.util.DateUtils;
import com.nuvola.gxpenses.client.util.EmptyDisplay;
import com.nuvola.gxpenses.client.web.application.ApplicationPresenter;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.event.BudgetElementsChangedEvent;
import com.nuvola.gxpenses.client.web.application.budget.popup.AddBudgetElementPresenter;
import com.nuvola.gxpenses.client.web.application.budget.widget.BudgetSiderPresenter;
import com.nuvola.gxpenses.shared.domaine.Budget;
import com.nuvola.gxpenses.shared.domaine.BudgetElement;
import com.nuvola.gxpenses.shared.dto.BudgetProgressTotal;
import org.fusesource.restygwt.client.Method;

import java.util.Date;
import java.util.List;

public class BudgetPresenter extends Presenter<BudgetPresenter.MyView, BudgetPresenter.MyProxy>
        implements BudgetUiHandlers, NoElementFoundEvent.NoElementFoundHandler,
        BudgetChangedEvent.BudgetChangedHandler, PopupClosedEvent.PopupClosedHandler,
        BudgetElementsChangedEvent.BudgetElementsChangedHandler {

    public interface MyView extends View, EmptyDisplay, HasUiHandlers<BudgetUiHandlers> {
        void setData(List<BudgetElement> data, BudgetProgressTotal total);

        void setBudgetName(String name);

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

    private final BudgetElementService budgetElementService;
    private final MessageBundle messageBundle;
    private final DateTimeFormat dateFormat;
    private final BudgetSiderPresenter budgetSiderPresenter;
    private final AddBudgetElementPresenter addBudgetElementPresenter;

    private Budget currentBudget;
    private Date currentDate;

    @Inject
    public BudgetPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                           final BudgetElementService budgetElementService, final MessageBundle messageBundle,
                           final BudgetSiderPresenter budgetSiderPresenter,
                           final AddBudgetElementPresenter addBudgetElementPresenter) {
        super(eventBus, view, proxy);

        this.budgetElementService = budgetElementService;
        this.messageBundle = messageBundle;
        this.budgetSiderPresenter = budgetSiderPresenter;
        this.addBudgetElementPresenter = addBudgetElementPresenter;
        this.dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");

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
        currentDate = new Date();
        currentBudget = event.getBudget();

        getView().setBudgetName(currentBudget.getName());
        getView().setPeriod(DateUtils.getDateToDisplay(currentDate, currentBudget.getPeriodicity()));

        if (getView().isEmptyVisible()) {
            getView().showElementsPanel();
        }

        fireLoadBudgetElementByIdAndPeriod();
    }

    @Override
    public void onPopupClosed(PopupClosedEvent event) {
        getView().switchBudgetSettingsStyle();
    }

    @Override
    public void onBudgetElementsChanged(BudgetElementsChangedEvent event) {
        fireLoadBudgetElementByIdAndPeriod();
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.TYPE_SetMainContent, this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        addRegisteredHandler(NoElementFoundEvent.getType(), this);
        addRegisteredHandler(BudgetChangedEvent.getType(), this);
        addRegisteredHandler(PopupClosedEvent.getType(), this);
        addRegisteredHandler(BudgetElementsChangedEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetVisibleSiderEvent.fire(this, budgetSiderPresenter);
    }

    private void fireLoadBudgetElementByIdAndPeriod() {
        budgetElementService.getBudgetElements(currentBudget.getId().toString(), dateFormat.format(currentDate),
                new MethodCallbackImpl<List<BudgetElement>>() {
            @Override
            public void onSuccess(Method method, List<BudgetElement> budgetElements) {
                getView().setData(budgetElements, new BudgetProgressTotal(currentBudget.getTotalAllowed(),
                        currentBudget.getTotalConsumed()));

                if (budgetElements.size() > 0) {
                    getView().hideNoBudgetsPanel();
                } else {
                    getView().showNoBudgetsPanel();
                }
            }
        });
    }

}
