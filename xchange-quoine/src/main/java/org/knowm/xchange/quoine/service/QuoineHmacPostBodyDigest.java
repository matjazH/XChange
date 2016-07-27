package org.knowm.xchange.quoine.service;

import net.iharder.Base64;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import java.util.Map;

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
