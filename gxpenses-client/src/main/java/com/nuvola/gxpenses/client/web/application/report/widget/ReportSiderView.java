package com.nuvola.gxpenses.client.web.application.report.widget;

import java.util.Arrays;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ValuePicker;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.resource.style.list.SiderMenuStyle;
import com.nuvola.gxpenses.client.web.application.renderer.EnumCell;

public class ReportSiderView extends ViewWithUiHandlers<ReportSiderUiHandlers> implements ReportSiderPresenter.MyView {
    public enum ReportEnum {
        SPENDING_BY_TAG("Spending By Tag"),
        SPENDING_OVER_TIME("Spending Over Time");

        private String label;

        private ReportEnum(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public interface Binder extends UiBinder<Widget, ReportSiderView> {
    }

    @UiField(provided = true)
    ValuePicker<ReportEnum> reportsMenu;

    @Inject
    ReportSiderView(Binder uiBinder,
                    SiderMenuStyle listResources) {
        reportsMenu = new ValuePicker<ReportEnum>(new CellList<ReportEnum>(new EnumCell<ReportEnum>(), listResources));
        reportsMenu.setAcceptableValues(Arrays.asList(ReportEnum.values()));
        reportsMenu.setValue(ReportEnum.SPENDING_BY_TAG);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setDefaultMenu() {
        reportsMenu.setValue(ReportEnum.SPENDING_BY_TAG, false);
    }

    @UiHandler("reportsMenu")
    void onMenuChanged(ValueChangeEvent<ReportEnum> event) {
        getUiHandlers().changeMenu(event.getValue());
    }
}
