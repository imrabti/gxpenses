package com.nuvola.gxpenses.client.web.application;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.web.GxpensesPresenter;
import com.nuvola.gxpenses.client.web.application.widget.HeaderPresenter;
import com.nuvola.gxpenses.client.web.application.widget.SiderHolderPresenter;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements GlobalMessageEvent.GlobalMessageHandler {

    public interface MyView extends View {
        public void showAjaxLoader(int timeout);

        public void hideAjaxLoader();

        public void displayMessage(String message);
    }

    @ProxyStandard
    public interface MyProxy extends Proxy<ApplicationPresenter> {
    }

    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();
    public static final Object TYPE_SetHeaderContent = new Object();
    public static final Object TYPE_SetSiderContent = new Object();

    private final HeaderPresenter headerPresenter;
    private final SiderHolderPresenter siderHolderPresenter;

    @Inject
    public ApplicationPresenter(final EventBus eventBus, final MyView view,
                                final MyProxy proxy, final HeaderPresenter headerPresenter,
                                final SiderHolderPresenter siderHolderPresenter) {
        super(eventBus, view, proxy);

        this.headerPresenter = headerPresenter;
        this.siderHolderPresenter = siderHolderPresenter;
    }

    @Override
    public void onMessageRecieved(GlobalMessageEvent event) {
        getView().displayMessage(event.getMessage());
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, GxpensesPresenter.TYPE_SetMainContent, this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        addRegisteredHandler(GlobalMessageEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        setInSlot(TYPE_SetHeaderContent, headerPresenter);
        setInSlot(TYPE_SetSiderContent, siderHolderPresenter);
    }

}
