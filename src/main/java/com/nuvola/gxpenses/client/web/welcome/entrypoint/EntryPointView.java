package com.nuvola.gxpenses.client.web.welcome.entrypoint;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewImpl;
import com.nuvola.gxpenses.client.web.GxpensesPresenter;

public class EntryPointView extends ViewImpl implements EntryPointPresenter.MyView {

    public interface Binder extends UiBinder<Widget, EntryPointView> {
    }

    @Inject
    public EntryPointView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (content != null) {
            if (slot == GxpensesPresenter.TYPE_SetMainContent) {
                main.setWidget(content);
            }
        }
    }

}
