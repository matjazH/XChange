package org.knowm.xchange.btcup.service;

import javax.crypto.Mac;
import java.math.BigInteger;

import si.mazi.rescu.RestInvocation;
import org.knowm.xchange.service.BaseParamsDigest;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpDigest extends BaseParamsDigest {

  private BtcUpDigest(String secretKey) {
    super(secretKey, HMAC_SHA_512);
  }

  public static BtcUpDigest createInstance(String secretKeyBase64) {
    return secretKeyBase64 == null ? null : new BtcUpDigest(secretKeyBase64);
  }

  @Override
  public String digestParams(RestInvocation restInvocationParam) {
    Mac mac = getMac();
    mac.update(restInvocationParam.getRequestBody().getBytes());
    return String.format("%0128x", new BigInteger(1, mac.doFinal()));
  }
}