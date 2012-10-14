package com.nuvola.gxpenses.client.web.application.budget;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.nuvola.gxpenses.client.event.SetVisibleSiderEvent;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.BudgetService;
import com.nuvola.gxpenses.client.util.EmptyDisplay;
import com.nuvola.gxpenses.client.web.application.ApplicationPresenter;
import com.nuvola.gxpenses.client.web.application.budget.widget.BudgetSiderPresenter;
import com.nuvola.gxpenses.shared.domaine.BudgetElement;
import com.nuvola.gxpenses.shared.dto.BudgetProgressTotal;

import java.util.List;

public class BudgetPresenter extends Presenter<BudgetPresenter.MyView, BudgetPresenter.MyProxy>
        implements BudgetUiHandlers {

    public interface MyView extends View, EmptyDisplay, HasUiHandlers<BudgetUiHandlers> {
        void setData(List<BudgetElement> data, BudgetProgressTotal total);

        void setBudgetName(String name);

        void setPeriod(String periodName);

        void showElementsPanel();

        void hideElementsPanel();

        void showEmptyPanel();

        void hideEmptyPanel();

        void switchBudgetSettingsStyle();

        Boolean isEmptyVisible();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.budget)
    public interface MyProxy extends ProxyPlace<BudgetPresenter> {
    }

    private final BudgetService budgetService;
    private final MessageBundle messageBundle;

    private final BudgetSiderPresenter budgetSiderPresenter;

    @Inject
    public BudgetPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                           final BudgetService budgetService, final MessageBundle messageBundle,
                           final BudgetSiderPresenter budgetSiderPresenter) {
        super(eventBus, view, proxy);

        this.budgetService = budgetService;
        this.messageBundle = messageBundle;
        this.budgetSiderPresenter = budgetSiderPresenter;

        getView().setUiHandlers(this);
    }

    public void elementSetting(Widget relativeTo) {

    }

    public void nextPeriod() {

    }

    public void previousPeriod() {

    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.TYPE_SetMainContent, this);
    }

    @Override
    protected void onBind() {
        super.onBind();
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetVisibleSiderEvent.fire(this, budgetSiderPresenter);
    }

}
