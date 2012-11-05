package com.nuvola.gxpenses.client.web.application;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.nuvola.gxpenses.client.event.GlobalMessageEvent;
import com.nuvola.gxpenses.client.event.SetVisibleSiderEvent;
import com.nuvola.gxpenses.client.web.GxpensesPresenter;
import com.nuvola.gxpenses.client.web.application.widget.HeaderPresenter;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements SetVisibleSiderEvent.SetVisibleSiderHandler, GlobalMessageEvent.GlobalMessageHandler {

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

    @Inject
    public ApplicationPresenter(final EventBus eventBus, final MyView view,
                                final MyProxy proxy, final HeaderPresenter headerPresenter) {
        super(eventBus, view, proxy);

        this.headerPresenter = headerPresenter;
    }

    @Override
    @ProxyEvent
    public void onVisibleSider(SetVisibleSiderEvent event) {
        setInSlot(TYPE_SetSiderContent, event.getSider());
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

        addRegisteredHandler(SetVisibleSiderEvent.getType(), this);
        addRegisteredHandler(GlobalMessageEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        setInSlot(TYPE_SetHeaderContent, headerPresenter);
    }

}
