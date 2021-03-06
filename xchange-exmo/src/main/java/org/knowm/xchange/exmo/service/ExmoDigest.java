package org.knowm.xchange.exmo.service;

import javax.crypto.Mac;

import org.knowm.xchange.service.BaseParamsDigest;

import si.mazi.rescu.RestInvocation;

import java.math.BigInteger;

public class ExmoDigest extends BaseParamsDigest {

  private ExmoDigest(String secretKeyBase64) {

    super(secretKeyBase64, HMAC_SHA_512);
  }

  public static ExmoDigest createInstance(String secretKeyBase64) {

    return secretKeyBase64 == null ? null : new ExmoDigest(secretKeyBase64);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    Mac mac256 = getMac();
    mac256.update(restInvocation.getRequestBody().getBytes());
    return String.format("%064x", new BigInteger(1, mac256.doFinal())).toLowerCase();
  }
}