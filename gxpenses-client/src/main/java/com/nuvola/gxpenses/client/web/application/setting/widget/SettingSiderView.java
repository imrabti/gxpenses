package com.nuvola.gxpenses.client.web.application.setting.widget;

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

public class SettingSiderView extends ViewWithUiHandlers<SettingSiderUiHandlers>
        implements SettingSiderPresenter.MyView {
    public enum SettingsEnum {
        GENERAL("General"),
        PASSWORD("Password"),
        TAGS("Tags");

        private String label;

        private SettingsEnum(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public interface Binder extends UiBinder<Widget, SettingSiderView> {
    }

    @UiField(provided = true)
    ValuePicker<SettingsEnum> settingsMenu;

    @Inject
    SettingSiderView(Binder uiBinder,
                     SiderMenuStyle listResources) {
        settingsMenu = new ValuePicker<SettingsEnum>(new CellList<SettingsEnum>(new EnumCell<SettingsEnum>(), listResources));
        settingsMenu.setAcceptableValues(Arrays.asList(SettingsEnum.values()));
        settingsMenu.setValue(SettingsEnum.GENERAL);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setDefaultMenu() {
        settingsMenu.setValue(SettingsEnum.GENERAL, false);
    }

    @UiHandler("settingsMenu")
    void onMenuChanged(ValueChangeEvent<SettingsEnum> event) {
        getUiHandlers().changeMenu(event.getValue());
    }
}
