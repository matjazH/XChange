package com.xeiam.xchange.quoine.service.polling;

import java.io.IOException;
import java.math.BigDecimal;

import com.xeiam.xchange.quoine.QuoineOrderType;
import com.xeiam.xchange.quoine.dto.marketdata.QuoineTradesList;
import si.mazi.rescu.HttpStatusIOException;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.quoine.QuoineUtils;
import com.xeiam.xchange.quoine.dto.trade.QuoineNewMarginOrderRequest;
import com.xeiam.xchange.quoine.dto.trade.QuoineNewOrderRequest;
import com.xeiam.xchange.quoine.dto.trade.QuoineOrderDetailsResponse;
import com.xeiam.xchange.quoine.dto.trade.QuoineOrderResponse;
import com.xeiam.xchange.quoine.dto.trade.QuoineOrdersList;

/**
 * @author gnandiga
 */
public class QuoineTradeServiceRaw extends QuoineBasePollingService {

  private boolean useMargin;
  private int leverageLevel;  
  
  /**
   * @param exchange
   */
  public QuoineTradeServiceRaw(Exchange exchange, boolean useMargin) {

    super(exchange);
    
    this.useMargin = useMargin;
    
    if(useMargin) {
    	leverageLevel = (Integer) exchange.getExchangeSpecification().getExchangeSpecificParametersItem("Leverage_Level");
    } else {
    	leverageLevel = 0;
    }
  }

  public QuoineOrderResponse placeLimitOrder(CurrencyPair currencyPair, String type, BigDecimal tradableAmount, BigDecimal price) throws IOException {

    QuoineNewOrderRequest quoineNewOrderRequest = useMargin ? 
    		new QuoineNewMarginOrderRequest("limit", QuoineUtils.toPairString(currencyPair), type, tradableAmount, price, leverageLevel) :
    		new QuoineNewOrderRequest("limit", QuoineUtils.toPairString(currencyPair), type, tradableAmount, price);
    try {
      return quoine.placeOrder(VENDOR, getDate(), nonceFactory, signatureCreator, quoineNewOrderRequest);
    } catch (HttpStatusIOException e) {
      throw handleHttpError(e);
    }
  }

  public QuoineOrderResponse placeMarketOrder(CurrencyPair currencyPair, String type, BigDecimal tradableAmount) throws IOException {

    QuoineNewOrderRequest quoineNewOrderRequest = useMargin ?
    		new QuoineNewMarginOrderRequest("market", QuoineUtils.toPairString(currencyPair), type, tradableAmount, null, leverageLevel) :
    		new QuoineNewOrderRequest("market", QuoineUtils.toPairString(currencyPair), type, tradableAmount, null);
    try {
      return quoine.placeOrder(VENDOR, getDate(), nonceFactory, signatureCreator, quoineNewOrderRequest);
    } catch (HttpStatusIOException e) {
      throw handleHttpError(e);
    }
  }

  public QuoineOrderResponse placeQuoineOrder(QuoineOrderType quoineOrderType, CurrencyPair currencyPair, String type, BigDecimal tradableAmount, BigDecimal price, Integer leverageLevel) throws IOException {

    QuoineNewOrderRequest quoineNewOrderRequest = new QuoineNewMarginOrderRequest(quoineOrderType.name(), QuoineUtils.toPairString(currencyPair), type, tradableAmount, price, leverageLevel);
    try {
      return quoine.placeOrder(VENDOR, getDate(), nonceFactory, signatureCreator, quoineNewOrderRequest);
    } catch (HttpStatusIOException e) {
      throw handleHttpError(e);
    }
  }

  public QuoineOrderResponse cancelQuoineOrder(String orderID) throws IOException {
    try {
      return quoine.cancelOrder(VENDOR, getDate(), nonceFactory, signatureCreator, orderID);
    } catch (HttpStatusIOException e) {
      throw handleHttpError(e);
    }
  }

  public QuoineOrderDetailsResponse getQuoineOrderDetails(String orderID) throws IOException {
    try {
      return quoine.orderDetails(getDate(), nonceFactory, signatureCreator, orderID);
    } catch (HttpStatusIOException e) {
      throw handleHttpError(e);
    }
  }

  public QuoineOrdersList listQuoineOrders(String currencyPair) throws IOException {
    try {
      return quoine.listOrders(getDate(), nonceFactory, signatureCreator, currencyPair);
    } catch (HttpStatusIOException e) {
      throw handleHttpError(e);
    }
  }


  public QuoineTradesList listQuoineExecutions(String currencyPair) throws IOException {
    try {
      return quoine.listExecutions(VENDOR, getDate(), nonceFactory, signatureCreator, currencyPair, 50, null);
    } catch (HttpStatusIOException e) {
      throw handleHttpError(e);
    }
  }
}
