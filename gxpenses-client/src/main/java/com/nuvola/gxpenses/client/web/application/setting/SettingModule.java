package com.nuvola.gxpenses.client.web.application.setting;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.mvp.uihandler.SetterUiHandlersStrategy;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.web.application.setting.renderer.TagCellFactory;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingUiHandlers;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingView;
import com.nuvola.gxpenses.client.web.application.setting.widget.PasswordSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.PasswordSettingUiHandlers;
import com.nuvola.gxpenses.client.web.application.setting.widget.PasswordSettingView;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderUiHandlers;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderView;
import com.nuvola.gxpenses.client.web.application.setting.widget.TagSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.TagSettingUiHandlers;
import com.nuvola.gxpenses.client.web.application.setting.widget.TagSettingView;

public class SettingModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<UiHandlersStrategy<SettingSiderUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<SettingSiderUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<GeneralSettingUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<GeneralSettingUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<PasswordSettingUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<PasswordSettingUiHandlers>>() {});
        bind(new TypeLiteral<UiHandlersStrategy<TagSettingUiHandlers>>() {})
                .to(new TypeLiteral<SetterUiHandlersStrategy<TagSettingUiHandlers>>() {});

        bindPresenter(SettingPresenter.class, SettingPresenter.MyView.class, SettingView.class,
                SettingPresenter.MyProxy.class);

        bindSingletonPresenterWidget(SettingSiderPresenter.class, SettingSiderPresenter.MyView.class,
                SettingSiderView.class);
        bindSingletonPresenterWidget(GeneralSettingPresenter.class, GeneralSettingPresenter.MyView.class,
                GeneralSettingView.class);
        bindSingletonPresenterWidget(PasswordSettingPresenter.class, PasswordSettingPresenter.MyView.class,
                PasswordSettingView.class);
        bindSingletonPresenterWidget(TagSettingPresenter.class, TagSettingPresenter.MyView.class,
                TagSettingView.class);

        install(new GinFactoryModuleBuilder().build(TagCellFactory.class));
    }

}
