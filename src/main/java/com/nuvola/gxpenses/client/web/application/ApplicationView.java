package com.nuvola.gxpenses.client.web.application;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewImpl;
import com.nuvola.gxpenses.client.web.application.ui.AjaxLoader;
import com.nuvola.gxpenses.client.web.application.ui.GlobalMessage;
import com.nuvola.gxpenses.client.web.application.widget.FooterView;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {

    public interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    SimplePanel headerDisplay;

    @UiField
    SimpleLayoutPanel mainDisplay;

    @UiField
    SimplePanel siderDisplay;

    @UiField(provided = true)
    FooterView footer;

    @UiField(provided = true)
    GlobalMessage globalMessage;

    @UiField(provided = true)
    AjaxLoader ajaxLoader;

    @Inject
    public ApplicationView(final Binder uiBinder, final FooterView footer,
                           final GlobalMessage globalMessage, final AjaxLoader ajaxLoader) {
        this.footer = footer;
        this.globalMessage = globalMessage;
        this.ajaxLoader = ajaxLoader;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (content != null) {
            if (slot == ApplicationPresenter.TYPE_SetMainContent) {
                mainDisplay.setWidget(content);
            } else if(slot == ApplicationPresenter.TYPE_SetHeaderContent) {
                headerDisplay.setWidget(content);
            } else if(slot == ApplicationPresenter.TYPE_SetSiderContent) {
                siderDisplay.setWidget(content);
            }
        }
    }

    @Override
    public void hideLoading() {
        Element loading = Document.get().getElementById("loading");
        loading.getParentElement().removeChild(loading);
    }

    @Override
    public void showAjaxLoader(int timeout) {
        ajaxLoader.display(timeout);
    }

    @Override
    public void hideAjaxLoader() {
        ajaxLoader.hide();
    }

    @Override
    public void displayMessage(String message) {
        globalMessage.displayMessage(message);
    }

}
