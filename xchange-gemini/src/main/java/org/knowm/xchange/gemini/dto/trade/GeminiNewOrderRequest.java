package org.knowm.xchange.gemini.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.gemini.dto.GeminiBaseRequest;

/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiNewOrderRequest extends GeminiBaseRequest {
/*
  client_order_id	string	Optional. A client-specified order id
  symbol	string	The symbol for the new order
  amount	string	Quoted decimal amount to purchase
  price	string	Quoted decimal amount to spend per unit
  side	string	“buy” or “sell”
  type	string	The order type. Only “exchange limit” supported through this API
  options	array	Optional. An optional array containing at most one supported order execution option. See Order execution options for details.
*/


  @JsonProperty("client_order_id")
  private String clientOrderId;

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("price")
  private String price;

  @JsonProperty("side")
  private String side;

  @JsonProperty("type")
  private String type;

  @JsonProperty("options")
  private String[] options;

  public GeminiNewOrderRequest(String symbol) {
    this.symbol = symbol;
  }

  public GeminiNewOrderRequest(String symbol, String amount, String price, String side, String type) {
    this.symbol = symbol;
    this.amount = amount;
    this.price = price;
    this.side = side;
    this.type = type;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getSide() {
    return side;
  }

  public void setSide(String side) {
    this.side = side;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String[] getOptions() {
    return options;
  }

  public void setOptions(String[] options) {
    this.options = options;
  }
}
