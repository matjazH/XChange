package org.knowm.xchange.gemini.service;

import org.knowm.xchange.gemini.GeminiAuthenticated;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

public class GeminiHmacDigest extends BaseParamsDigest {

  /**
   * Constructor
   *
   * @param secretKeyBase64
   * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded key is invalid).
   */
  private GeminiHmacDigest(String secretKeyBase64) {

    super(secretKeyBase64, HMAC_SHA_384);
  }

  public static GeminiHmacDigest createInstance(String secretKeyBase64) {

    return secretKeyBase64 == null ? null : new GeminiHmacDigest(secretKeyBase64);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    try {
      String payload = restInvocation.getHttpHeadersFromParams().get(GeminiAuthenticated.HEADER_PAYLOAD);

      Mac mac = getMac();
      mac.update(payload.getBytes("UTF-8"));
      String encodeBytes = String.format("%096x", new BigInteger(1, mac.doFinal()));

      return encodeBytes;
    } catch (IOException e) {
      throw new RuntimeException("Illegal encoding, check the code.", e);
    }
  }
}
