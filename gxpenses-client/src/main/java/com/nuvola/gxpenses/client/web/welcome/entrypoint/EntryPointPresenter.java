package com.nuvola.gxpenses.client.web.welcome.entrypoint;

import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.nuvola.gxpenses.client.web.GxpensesPresenter;

public class EntryPointPresenter extends Presenter<EntryPointPresenter.MyView, EntryPointPresenter.MyProxy> {

    public interface MyView extends View {
    }

    @ProxyStandard
    public interface MyProxy extends Proxy<EntryPointPresenter> {
    }

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> TYPE_SetMainContent = new GwtEvent.Type<RevealContentHandler<?>>();

    @Inject
    public EntryPointPresenter(final EventBus eventBus, final MyView view,
                             final MyProxy proxy) {
        super(eventBus, view, proxy);
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, GxpensesPresenter.TYPE_SetMainContent, this);
    }

}
