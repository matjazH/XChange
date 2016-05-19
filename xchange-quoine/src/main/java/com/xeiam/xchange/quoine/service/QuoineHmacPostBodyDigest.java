package com.xeiam.xchange.quoine.service;

import com.xeiam.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;
import si.mazi.rescu.utils.Base64;

import javax.crypto.Mac;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class QuoineHmacPostBodyDigest extends BaseParamsDigest {

  private static final String FIELD_SEPARATOR = ",";

  private final String userId;

  /**
   * Constructor
   *
   * @param secretKeyBase64
   * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded key is invalid).
   */
  private QuoineHmacPostBodyDigest(String userId, String secretKeyBase64) {
    super(secretKeyBase64, HMAC_SHA_1);
    this.userId = userId;
  }

  public static QuoineHmacPostBodyDigest createInstance(String userId, String secretKeyBase64) {
    return secretKeyBase64 == null ? null : new QuoineHmacPostBodyDigest(userId, secretKeyBase64);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    Map<String, String> httpHeadersFromParams = restInvocation.getHttpHeadersFromParams();

    StringBuilder stringBuilder = new StringBuilder()
        .append(restInvocation.getReqContentType()).append(FIELD_SEPARATOR)
        .append(FIELD_SEPARATOR)
        .append(restInvocation.getPath());

    String queryString = restInvocation.getQueryString();
    if (queryString != null && !queryString.isEmpty()) {
      stringBuilder.append("?").append(queryString);
    }

    stringBuilder.append(FIELD_SEPARATOR)
        .append(httpHeadersFromParams.get("Date")).append(FIELD_SEPARATOR)
        .append(httpHeadersFromParams.get("NONCE"));

    Mac mac512 = getMac();
    mac512.update(stringBuilder.toString().getBytes());

    return "APIAuth " + userId + ":" + Base64.encodeBytes(mac512.doFinal()).trim();
  }
}
