package com.nuvola.gxpenses.client.gin;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.nuvola.gxpenses.client.BootStrapper;
import com.nuvola.gxpenses.client.resource.Resources;
import com.nuvola.gxpenses.client.web.GxpensesPresenter;
import com.nuvola.gxpenses.client.web.application.ApplicationPresenter;
import com.nuvola.gxpenses.client.web.application.budget.BudgetPresenter;
import com.nuvola.gxpenses.client.web.application.setting.SettingPresenter;
import com.nuvola.gxpenses.client.web.application.transaction.TransactionPresenter;

@GinModules(value = {ClientModule.class})
public interface ClientGinjector extends Ginjector {

    EventBus getEventBus();

    PlaceManager getPlaceManager();

    Resources getResources();

    BootStrapper getBootStrapper();

    Provider<GxpensesPresenter> getGxpensesPresenter();

    Provider<ApplicationPresenter> getApplicationPresenter();

    Provider<TransactionPresenter> getTransactionPresenter();

    AsyncProvider<BudgetPresenter> getBudgetPresenter();

    AsyncProvider<SettingPresenter> getSettingPresenter();

}
