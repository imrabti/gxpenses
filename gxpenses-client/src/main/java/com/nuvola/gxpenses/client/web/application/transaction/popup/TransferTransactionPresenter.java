/*
 * Copyright (c) 2014 by Nuvola Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Nuvola Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.nuvola.gxpenses.client.web.application.transaction.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.nuvola.gxpenses.client.event.PopupClosedEvent;
import com.nuvola.gxpenses.client.resource.message.MessageBundle;
import com.nuvola.gxpenses.client.rest.TransactionService;
import com.nuvola.gxpenses.common.shared.dto.TransferTransaction;

;

public class TransferTransactionPresenter extends PresenterWidget<TransferTransactionPresenter.MyView>
        implements TransferTransactionUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<TransferTransactionUiHandlers> {
        void showRelativeTo(Widget widget);

        void edit(TransferTransaction transferTransaction);
    }

    private final RestDispatchAsync dispatcher;
    private final TransactionService transactionService;
    private final MessageBundle messageBundle;

    private TransferTransaction transferTransaction;
    private Widget relativeTo;

    @Inject
    TransferTransactionPresenter(EventBus eventBus,
                                 MyView view,
                                 RestDispatchAsync dispatcher,
                                 TransactionService transactionService,
                                 MessageBundle messageBundle) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.transactionService = transactionService;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveTransfer(TransferTransaction transferTransaction) {
        // TODO : Adapt using only client side ...
    }

    @Override
    public void close() {
        PopupClosedEvent.fire(this);
    }

    public void setRelativeTo(Widget relativeTo) {
        this.relativeTo = relativeTo;
    }

    @Override
    protected void onReveal() {
        transferTransaction = new TransferTransaction();
        getView().edit(transferTransaction);
        getView().showRelativeTo(relativeTo);
    }
}
