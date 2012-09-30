package com.nuvola.gxpenses.client.mvp.uihandler;

import com.gwtplatform.mvp.client.UiHandlers;

public interface UiHandlersStrategy<H extends UiHandlers> {

    void setUiHandlers(H uiHandlers);

    H getUiHandlers();
}
