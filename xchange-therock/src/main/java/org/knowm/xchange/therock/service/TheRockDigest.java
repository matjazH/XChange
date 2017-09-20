package org.knowm.xchange.therock.service;

import java.math.BigInteger;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.HeaderParam;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestInvocation;


public class TheRockDigest
    implements ParamsDigest {
  public static final String HMAC_SHA_512 = "HmacSHA512";
  private final Mac mac;

  public TheRockDigest(String secretKeyStr) {
    try {
      SecretKey secretKey = new SecretKeySpec(secretKeyStr.getBytes(), "HmacSHA512");
      this.mac = Mac.getInstance("HmacSHA512");
      this.mac.init(secretKey);
    } catch (Exception e) {
      throw new RuntimeException("Error initializing The Rock Signer", e);
    }
  }

  public String digestParams(RestInvocation restInvocation) {
    String nonce = restInvocation.getParamValue(HeaderParam.class, "X-TRT-NONCE").toString();
    this.mac.update(nonce.getBytes());
    this.mac.update(restInvocation.getInvocationUrl().getBytes());

    return String.format("%0128x", new Object[]{new BigInteger(1, this.mac.doFinal())});
  }
}