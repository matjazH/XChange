package org.knowm.xchange.bitmex.service;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import java.math.BigInteger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class BitMexDigest implements ParamsDigest {

  private final Mac mac;
  private static final String HMAC_SHA_512 = "HmacSHA512";

  @Override
  public String digestParams(RestInvocation restInvocation) {
    mac.update(restInvocation.getRequestBody().getBytes());

    return String.format("%0128x", new BigInteger(1, mac.doFinal()));
  }

  private BitMexDigest(String secretKeyBase) throws IllegalArgumentException {
    try {
      SecretKey secretKey = new SecretKeySpec(secretKeyBase.getBytes(), HMAC_SHA_512);
      mac = Mac.getInstance(HMAC_SHA_512);
      mac.init(secretKey);
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException("Invalid key for hmac initialization.", e);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Illegal algorithm for post body digest. Check the implementation.");
    }
  }

  public static BitMexDigest createInstance(String secretKeyBase) {
    return (secretKeyBase == null || secretKeyBase.isEmpty()) ? null : new BitMexDigest(secretKeyBase);
  }
}
