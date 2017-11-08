package org.knowm.xchange.hitbtc.service;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.BasicAuthCredentials;
import si.mazi.rescu.RestInvocation;

public class HitbtcHmacDigest extends BaseParamsDigest {

  private final String exchangeAccessKey;
  private final String exchangeSecretKey;

  public HitbtcHmacDigest(String exchangeAccessKey, String exchangeSecretKey) {

    super(exchangeSecretKey, HMAC_SHA_1);

    this.exchangeAccessKey = exchangeAccessKey;
    this.exchangeSecretKey = exchangeSecretKey;
  }

  public static HitbtcHmacDigest createInstance(String exchangeAccessKey, String exchangeSecretKey) {

    return exchangeSecretKey == null ? null : new HitbtcHmacDigest(exchangeAccessKey, exchangeSecretKey);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {
    BasicAuthCredentials auth = new BasicAuthCredentials(exchangeAccessKey, exchangeSecretKey);

    return auth.digestParams(restInvocation);
  }
}
