package com.nuvola.gxpenses.client.web.application.setting;


import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingUiHandlers;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingView;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderUiHandlers;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderView;

public class SettingModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<UiHandlersStrategy<SettingSiderUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<SettingSiderUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<GeneralSettingUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<GeneralSettingUiHandlers>>() {});

        bindPresenter(SettingPresenter.class, SettingPresenter.MyView.class, SettingView.class,
                SettingPresenter.MyProxy.class);

        bindSingletonPresenterWidget(SettingSiderPresenter.class, SettingSiderPresenter.MyView.class,
                SettingSiderView.class);
        bindSingletonPresenterWidget(GeneralSettingPresenter.class, GeneralSettingPresenter.MyView.class,
                GeneralSettingView.class);
    }

}
