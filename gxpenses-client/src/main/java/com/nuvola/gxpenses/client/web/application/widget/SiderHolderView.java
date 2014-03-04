package com.nuvola.gxpenses.client.web.application.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewImpl;

public class SiderHolderView extends ViewImpl implements SiderHolderPresenter.MyView {

    public interface Binder extends UiBinder<Widget, SiderHolderView> {
    }

    @UiField
    SimplePanel siderContainer;

    @Inject
    public SiderHolderView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        siderContainer.clear();
        if (content != null) {
            siderContainer.setWidget(content);
        }
    }

}
