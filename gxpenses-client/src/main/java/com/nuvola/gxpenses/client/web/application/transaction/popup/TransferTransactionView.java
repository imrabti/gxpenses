package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import com.nuvola.gxpenses.client.web.application.transaction.popup.ui.TransferTransactionEditor;
import com.nuvola.gxpenses.common.shared.dto.TransferTransaction;

public class TransferTransactionView extends PopupViewWithUiHandlers<TransferTransactionUiHandlers>
        implements TransferTransactionPresenter.MyView {
    public interface Binder extends UiBinder<Widget, TransferTransactionView> {
    }

    @UiField
    PopupPanel popup;
    @UiField(provided = true)
    TransferTransactionEditor transferTransactionEditor;

    @Inject
    TransferTransactionView(EventBus eventBus,
                            Binder uiBinder,
                            TransferTransactionEditor transferTransactionEditor) {
        super(eventBus);

        this.transferTransactionEditor = transferTransactionEditor;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("popup")
    void onClose(CloseEvent<PopupPanel> closeEvent) {
        getUiHandlers().close();
    }

    @UiHandler("save")
    void onSave(ClickEvent event) {
        TransferTransaction transfer = transferTransactionEditor.get();
        if (transfer != null) {
            getUiHandlers().saveTransfer(transfer);
            getUiHandlers().close();
            hide();
        }
    }

    @UiHandler("cancel")
    void onCancel(ClickEvent event) {
        getUiHandlers().close();
        hide();
    }

    @Override
    public void showRelativeTo(Widget widget) {
        int left = widget.getAbsoluteLeft() - (popup.getOffsetWidth() - widget.getOffsetWidth());
        int top = widget.getAbsoluteTop() + widget.getOffsetHeight();
        setPosition(left, top);
    }

    @Override
    public void edit(TransferTransaction transfer) {
        transferTransactionEditor.edit(transfer);
    }
}
