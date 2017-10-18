package org.knowm.xchange.ccex.service;

import java.util.List;
import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ccex.dto.trade.*;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;

public class CCEXTradeServiceRaw extends CCEXBaseService
{

  public CCEXTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<CCEXOpenorder> getCCEXOpenOrders() throws IOException {
    CCEXGetopenordersResponse response = cCEXAuthenticated.getopenorders(apiKey, signatureCreator,
        exchange.getNonceFactory());

    if(response.isSuccess()) return response.getResult();
    else {
      throw new ExchangeException(response.getMessage());
    }

  }

  public CCEXGetorderinfoResponse getOrderInfo(String uuid) throws IOException {
    return cCEXAuthenticated.getorderinfo(apiKey,signatureCreator,exchange.getNonceFactory(),uuid);
  }

  public List<CCEXOrderhistory> getCCEXTradeHistory() throws IOException {

    CCEXGetorderhistoryResponse response;
    response=cCEXAuthenticated.getorderhistory(apiKey,signatureCreator,exchange.getNonceFactory());

    if(response.isSuccess()) return response.getResult();
    else {
      throw new ExchangeException(response.getMessage());
    }
  }

  public boolean cancelCCEXLimitOrder(String uuid) throws IOException {

    CCEXCancelResponse response = cCEXAuthenticated.cancel(apiKey, signatureCreator, exchange.getNonceFactory(), uuid);

    if (response.isSuccess()) {
      return true;
    } else {
      throw new ExchangeException(response.getMessage());
    }

  }

  public String placeCCEXLimitOrder(LimitOrder limitOrder) throws IOException {

    String lpair = limitOrder.getCurrencyPair().base.toString().toLowerCase();
    String rpair = limitOrder.getCurrencyPair().counter.toString().toLowerCase();

    if (limitOrder.getType() == OrderType.BID) {
      CCEXBuySellLimitResponse response = cCEXAuthenticated.buylimit(apiKey, signatureCreator,
          exchange.getNonceFactory(), lpair, rpair, limitOrder.getTradableAmount().toPlainString(),
          limitOrder.getLimitPrice().toPlainString());

      if (response.isSuccess()) {
        return response.getResult().getUuid();
      } else {
        throw new ExchangeException(response.getMessage());
      }

    } else {
      CCEXBuySellLimitResponse response = cCEXAuthenticated.selllimit(apiKey, signatureCreator,
          exchange.getNonceFactory(), lpair, rpair, limitOrder.getTradableAmount().toPlainString(),
          limitOrder.getLimitPrice().toPlainString());

      if (response.isSuccess()) {
        return response.getResult().getUuid();
      } else {
        throw new ExchangeException(response.getMessage());
      }
    }
  }
}