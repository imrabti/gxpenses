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
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;

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
    public GxpensesPresenter(final EventBus eventBus, final MyView view,
                             final MyProxy proxy) {
        super(eventBus, view, proxy);
    }

    @Override
    protected void revealInParent() {
        RevealRootLayoutContentEvent.fire(this, this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        getView().hideLoading();
    }

}
