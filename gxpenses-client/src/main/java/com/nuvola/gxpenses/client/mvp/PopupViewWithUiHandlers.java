package com.nuvola.gxpenses.client.mvp;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public abstract class PopupViewWithUiHandlers<H extends UiHandlers> extends PopupViewImpl
        implements UiHandlersStrategy<H> {
    private UiHandlersStrategy<H> uiHandlersStrategy;

    public PopupViewWithUiHandlers(EventBus eventBus, UiHandlersStrategy<H> uiHandlersStrategy) {
        super(eventBus);

        this.uiHandlersStrategy = uiHandlersStrategy;
    }

    @Override
    public H getUiHandlers() {
        return uiHandlersStrategy.getUiHandlers();
    }

    @Override
    public void setUiHandlers(H uiHandlers) {
        uiHandlersStrategy.setUiHandlers(uiHandlers);
    }
}
