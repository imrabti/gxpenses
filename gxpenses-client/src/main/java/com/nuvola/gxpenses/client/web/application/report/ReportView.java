package com.nuvola.gxpenses.client.web.application.report;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewImpl;

public class ReportView extends ViewImpl implements ReportPresenter.MyView {
    public interface Binder extends UiBinder<Widget, ReportView> {
    }

    @UiField
    SimpleLayoutPanel mainPanel;

    @Inject
    public ReportView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (content != null) {
            if (slot == ReportPresenter.TYPE_SetMainContent) {
                mainPanel.setWidget(content);
            }
        }
    }
}
