package com.nuvola.gxpenses.client.web.application.setting;

import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.nuvola.gxpenses.client.event.SetVisibleSiderEvent;
import com.nuvola.gxpenses.client.place.NameTokens;
import com.nuvola.gxpenses.client.web.application.ApplicationPresenter;
import com.nuvola.gxpenses.client.web.application.setting.event.SettingsMenuChangedEvent;
import com.nuvola.gxpenses.client.web.application.setting.importfile.ImportRes;
import com.nuvola.gxpenses.client.web.application.setting.widget.GeneralSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.PasswordSettingPresenter;
import com.nuvola.gxpenses.client.web.application.setting.widget.SettingSiderPresenter;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class SettingPresenter extends Presenter<SettingPresenter.MyView, SettingPresenter.MyProxy>
        implements SettingsMenuChangedEvent.SettingsChangedEventHandler {

    public interface MyView extends View {
        void showErrors(Set<ConstraintViolation<?>> violations);

        void clearErrors();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.setting)
    public interface MyProxy extends ProxyPlace<SettingPresenter> {
    }

    public static final Object TYPE_SetMainContent = new Object();

    private final SettingSiderPresenter settingSiderPresenter;
    private final GeneralSettingPresenter generalSettingPresenter;
    private final PasswordSettingPresenter passwordSettingPresenter;
    private final ImportRes importRes;

    @Inject
    public SettingPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                            final SettingSiderPresenter settingSiderPresenter,
                            final GeneralSettingPresenter generalSettingPresenter,
                            final PasswordSettingPresenter passwordSettingPresenter,
                            final ImportRes importRes) {
        super(eventBus, view, proxy);

        this.settingSiderPresenter = settingSiderPresenter;
        this.generalSettingPresenter = generalSettingPresenter;
        this.passwordSettingPresenter = passwordSettingPresenter;
        this.importRes = importRes;
    }

    @Override
    public void onSettingsChanged(SettingsMenuChangedEvent event) {
        switch (event.getSelectedMenu()) {
            case GENERAL:
                setInSlot(TYPE_SetMainContent, generalSettingPresenter);
                break;
            case PASSWORD:
                setInSlot(TYPE_SetMainContent, passwordSettingPresenter);
                break;
            default:
                setInSlot(TYPE_SetMainContent, generalSettingPresenter);
                break;
        }
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.TYPE_SetMainContent, this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        addRegisteredHandler(SettingsMenuChangedEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetVisibleSiderEvent.fire(this, settingSiderPresenter);
        setInSlot(TYPE_SetMainContent, generalSettingPresenter);
    }

    private void parseFile() throws ResourceException {
       importRes.qifSample().getText(new ResourceCallback<TextResource>() {
           public void onError(ResourceException e) {}

           public void onSuccess(TextResource r) {
           }
       });
    }

}
