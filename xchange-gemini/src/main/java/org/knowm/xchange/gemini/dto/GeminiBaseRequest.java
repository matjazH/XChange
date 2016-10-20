package org.knowm.xchange.gemini.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.iharder.Base64;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestInvocation;


/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiBaseRequest implements ParamsDigest {


  @JsonProperty("request")
  private String request;

  @JsonProperty("nonce")
  private Long nonce;

  public GeminiBaseRequest() {
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public Long getNonce() {
    return nonce;
  }

  public void setNonce(Long nonce) {
    this.nonce = nonce;
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    String path = "/" + restInvocation.getPath();
    this.setRequest(path);

    ObjectMapper objectMapper = new ObjectMapper();

    String payloadJson;
    try {
      payloadJson = objectMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }

    String b64 = Base64.encodeBytes(payloadJson.getBytes());

    return b64;
  }
}
