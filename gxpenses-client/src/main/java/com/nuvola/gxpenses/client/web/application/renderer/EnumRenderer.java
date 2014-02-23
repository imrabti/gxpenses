package com.nuvola.gxpenses.client.web.application.renderer;

import com.google.gwt.text.shared.AbstractRenderer;

public class EnumRenderer<T> extends AbstractRenderer<T> {

    @Override
    public String render(T object) {
        return object == null ? "" : object.toString();
    }

}
