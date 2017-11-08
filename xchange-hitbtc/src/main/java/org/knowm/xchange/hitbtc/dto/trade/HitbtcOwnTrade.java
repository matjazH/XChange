package org.knowm.xchange.hitbtc.dto.trade;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitbtcOwnTrade {

  private final long tradeId;
  private final BigDecimal execPrice;
  private Date timestamp;
  private final long originalOrderId;
  private final BigDecimal fee;
  private final String clientOrderId;
  private final String symbol;
  private final String side;
  private final BigDecimal execQuantity;

  public HitbtcOwnTrade(@JsonProperty("id") long tradeId,
                        @JsonProperty("clientOrderId") String clientOrderId,
                        @JsonProperty("symbol") String symbol,
                        @JsonProperty("orderId") long originalOrderId,
                        @JsonProperty("side") String side,
                        @JsonProperty("quantity") BigDecimal execQuantity,
                        @JsonProperty("price") BigDecimal execPrice,
                        @JsonProperty("fee") BigDecimal fee,
                        @JsonProperty("timestamp") String timestamp) {

    super();
    this.tradeId = tradeId;
    this.execPrice = execPrice;
    try {
      this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(timestamp);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    this.originalOrderId = originalOrderId;
    this.fee = fee;
    this.clientOrderId = clientOrderId;
    this.symbol = symbol;
    this.side = side;
    this.execQuantity = execQuantity;
  }

  public long getTradeId() {

    return tradeId;
  }

  public BigDecimal getExecPrice() {

    return execPrice;
  }

  public long getTimestamp() {

    return timestamp.getTime();
  }

  public long getOriginalOrderId() {

    return originalOrderId;
  }

  public BigDecimal getFee() {

    return fee;
  }

  public String getClientOrderId() {

    return clientOrderId;
  }

  public String getSymbol() {

    return symbol;
  }

  public String getSide() {

    return side;
  }

  public BigDecimal getExecQuantity() {

    return execQuantity;
  }

  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder();
    builder.append("HitbtcTrade [tradeId=");
    builder.append(tradeId);
    builder.append(", execPrice=");
    builder.append(execPrice);
    builder.append(", timestamp=");
    builder.append(timestamp);
    builder.append(", originalOrderId=");
    builder.append(originalOrderId);
    builder.append(", fee=");
    builder.append(fee);
    builder.append(", clientOrderId=");
    builder.append(clientOrderId);
    builder.append(", symbol=");
    builder.append(symbol);
    builder.append(", side=");
    builder.append(side);
    builder.append(", execQuantity=");
    builder.append(execQuantity);
    builder.append("]");
    return builder.toString();
  }
}
