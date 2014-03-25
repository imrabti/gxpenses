package com.nuvola.gxpenses.client.web.application;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.web.GxpensesPresenter;
import com.nuvola.gxpenses.client.web.application.widget.HeaderPresenter;
import com.nuvola.gxpenses.client.web.application.widget.SiderHolderPresenter;
import com.nuvola.gxpenses.common.client.event.RequestEvent;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements GlobalMessageEvent.GlobalMessageHandler, RequestEvent.RequestEventHandler {
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

    private static final int LOADING_TIMEOUT = 250;

    private final HeaderPresenter headerPresenter;
    private final SiderHolderPresenter siderHolderPresenter;

    @Inject
    ApplicationPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         HeaderPresenter headerPresenter,
                         SiderHolderPresenter siderHolderPresenter) {
        super(eventBus, view, proxy, GxpensesPresenter.TYPE_SetMainContent);

        this.headerPresenter = headerPresenter;
        this.siderHolderPresenter = siderHolderPresenter;
    }

    @Override
    public void onMessageRecieved(GlobalMessageEvent event) {
        getView().displayMessage(event.getMessage());
    }

    @Override
    public void onRequestEvent(RequestEvent requestEvent) {
        if (requestEvent.getState() == RequestEvent.State.SENT) {
            getView().showAjaxLoader(LOADING_TIMEOUT);
        } else {
            getView().hideAjaxLoader();
        }
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(GlobalMessageEvent.getType(), this);
        addRegisteredHandler(RequestEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        setInSlot(TYPE_SetHeaderContent, headerPresenter);
        setInSlot(TYPE_SetSiderContent, siderHolderPresenter);
    }
}
