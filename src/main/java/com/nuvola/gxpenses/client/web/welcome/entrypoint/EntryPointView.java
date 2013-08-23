package com.nuvola.gxpenses.client.web.welcome.entrypoint;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class EntryPointView extends ViewImpl implements EntryPointPresenter.MyView {

    public interface Binder extends UiBinder<Widget, EntryPointView> {
    }

    @UiField
    SimplePanel main;

    @Inject
    public EntryPointView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (content != null) {
            if (slot == EntryPointPresenter.TYPE_SetMainContent) {
                main.setWidget(content);
            }
        }
    }

}
