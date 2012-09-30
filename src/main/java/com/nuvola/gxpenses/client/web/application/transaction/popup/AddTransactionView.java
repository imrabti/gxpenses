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
import com.nuvola.gxpenses.client.mvp.PopupViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.web.application.transaction.popup.ui.TransactionEditor;
import com.nuvola.gxpenses.shared.domaine.Transaction;

public class AddTransactionView extends PopupViewWithUiHandlers<AddTransactionUiHandler>
        implements AddTransactionPresenter.MyView {

    public interface Binder extends UiBinder<Widget, AddTransactionView> {
    }

    @UiField
    PopupPanel popup;

    @UiField(provided = true)
    TransactionEditor transactionEditor;

    @Inject
	public AddTransactionView(final EventBus eventBus, final Binder uiBinder,
                              final UiHandlersStrategy<AddTransactionUiHandler> uiHandlersStrategy,
                              final TransactionEditor transactionEditor) {
        super(eventBus, uiHandlersStrategy);
        this.transactionEditor = transactionEditor;

        initWidget(uiBinder.createAndBindUi(this));
	}

    @UiHandler("popup")
    void onClose(CloseEvent<PopupPanel> closeEvent) {
        getUiHandlers().close();
    }

    @UiHandler("save")
    void onSave(ClickEvent event) {
        Transaction transaction = transactionEditor.get();
        if(transaction != null) {
            getUiHandlers().saveTransaction(transaction);
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
    public void edit(Transaction transaction) {
        transactionEditor.edit(transaction);
    }

}
