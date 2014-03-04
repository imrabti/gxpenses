package com.nuvola.gxpenses.client.web;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

public class GxpensesPresenter extends Presenter<GxpensesPresenter.MyView, GxpensesPresenter.MyProxy> {
    public interface MyView extends View {
        public void hideLoading();
    }

    @ProxyStandard
    public interface MyProxy extends Proxy<GxpensesPresenter> {
    }

    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

    @Inject
    GxpensesPresenter(EventBus eventBus,
                      MyView view,
                      MyProxy proxy) {
        super(eventBus, view, proxy, RevealType.RootLayout);
    }

    @Override
    protected void onReveal() {
        getView().hideLoading();
    }
}
