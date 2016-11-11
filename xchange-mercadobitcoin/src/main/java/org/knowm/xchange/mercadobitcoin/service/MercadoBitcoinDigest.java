package org.knowm.xchange.mercadobitcoin.service;

import java.math.BigInteger;

import javax.crypto.Mac;

import net.iharder.Base64;
import org.knowm.xchange.service.BaseParamsDigest;

import si.mazi.rescu.RestInvocation;

/**
 * @author Felipe Micaroni Lalli
 */
public class MercadoBitcoinDigest extends BaseParamsDigest {

  private MercadoBitcoinDigest(String secret) {

    super(secret, HMAC_SHA_512);
  }

  public static MercadoBitcoinDigest createInstance(String method, String pin, String signCode, long tonce) {

    return signCode == null ? null : new MercadoBitcoinDigest(signCode);
  }

  public static MercadoBitcoinDigest createInstance(String secret) {

    return secret == null ? null : new MercadoBitcoinDigest(secret);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    String message = "/" + restInvocation.getPath() + "?" + restInvocation.getRequestBody();
    Mac hmac512 = getMac();
    hmac512.update(message.getBytes());

    return String.format("%0128x", new BigInteger(1, hmac512.doFinal())).toLowerCase();
  }
}