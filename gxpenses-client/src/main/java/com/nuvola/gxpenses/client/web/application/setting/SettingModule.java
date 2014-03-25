package com.nuvola.gxpenses.client.web.application.setting;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.nuvola.gxpenses.client.web.application.setting.renderer.TagCellFactory;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingView;
import com.nuvola.gxpenses.client.web.application.setting.widget.PasswordSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.PasswordSettingView;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderView;
import com.nuvola.gxpenses.client.web.application.setting.widget.TagSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.TagSettingView;

public class SettingModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
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
