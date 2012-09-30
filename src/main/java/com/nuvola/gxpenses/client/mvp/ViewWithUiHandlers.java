package com.nuvola.gxpenses.client.mvp;

import com.gwtplatform.mvp.client.UiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public abstract class ViewWithUiHandlers<H extends UiHandlers> extends ViewImpl
        implements UiHandlersStrategy<H> {
    private UiHandlersStrategy<H> uiHandlersStrategy;

    public ViewWithUiHandlers(final UiHandlersStrategy<H> uiHandlersStrategy) {
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
