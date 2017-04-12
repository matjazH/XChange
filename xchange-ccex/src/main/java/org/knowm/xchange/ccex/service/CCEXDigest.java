package org.knowm.xchange.ccex.service;

import javax.crypto.Mac;

import java.math.BigInteger;

import si.mazi.rescu.RestInvocation;

import org.knowm.xchange.service.BaseParamsDigest;

public class CCEXDigest extends BaseParamsDigest {

  private CCEXDigest(String secretKey) {

    super(secretKey, HMAC_SHA_512);
  }

  public static CCEXDigest createInstance(String secretKeyBase64) {

    return secretKeyBase64 == null ? null : new CCEXDigest(secretKeyBase64);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    Mac mac = getMac();
    mac.update(restInvocation.getInvocationUrl().getBytes());
    return String.format("%0128x", new BigInteger(1, mac.doFinal()));
  }

}