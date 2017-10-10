package org.knowm.xchange.btce.v3.dto.trade;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.btce.v3.dto.WEXReturn;

/**
 * Return value from TradeHistory, including overal status and map of transaction ids to TransHistoryResult.
 *
 * @author Peter N. Steinmetz Date: 3/30/15 Time: 3:19 PM
 */
public class WEXTransHistoryReturn extends WEXReturn<Map<Long, WEXTransHistoryResult>> {

  /**
   * Constructor
   *
   * @param success
   * @param value
   * @param error
   */
  public WEXTransHistoryReturn(@JsonProperty("success") boolean success, @JsonProperty("return") Map<Long, WEXTransHistoryResult> value,
                               @JsonProperty("error") String error) {

    super(success, value, error);
  }

}
