package com.nuvola.gxpenses.client.web.welcome.entrypoint;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewImpl;

public class EntryPointView extends ViewImpl implements EntryPointPresenter.MyView {

    public interface Binder extends UiBinder<Widget, EntryPointView> {
    }

    @Inject
    public EntryPointView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
