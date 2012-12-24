package com.nuvola.gxpenses.client.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.nuvola.gxpenses.client.request.proxy.DataPageProxy;
import com.nuvola.gxpenses.client.request.proxy.PagedTransactionsProxy;
import com.nuvola.gxpenses.client.request.proxy.TransactionFilterProxy;
import com.nuvola.gxpenses.client.request.proxy.TransactionProxy;
import com.nuvola.gxpenses.client.request.proxy.TransferTransactionProxy;
import com.nuvola.gxpenses.server.service.TransactionServiceImpl;
import com.nuvola.gxpenses.server.util.SpringServiceLocator;

@Service(value = TransactionServiceImpl.class, locator = SpringServiceLocator.class)
public interface TransactionRequest extends RequestContext {
    Request<Void> createNewTransaction(TransactionProxy transaction);

    Request<Void> removeTransaction(Long transactionId);

    Request<Void> createNewTransferTransaction(TransferTransactionProxy transfer);

    Request<PagedTransactionsProxy> findByAccountAndDateAndType(TransactionFilterProxy filter, DataPageProxy dataPage);

    Request<Double> totalAmountByAccountAndPeriodAndType(TransactionFilterProxy filter);
}
