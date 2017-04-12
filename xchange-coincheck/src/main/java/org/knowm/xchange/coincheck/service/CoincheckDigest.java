package org.knowm.xchange.coincheck.service;

import org.knowm.xchange.coincheck.CoincheckAuthenticated;
import org.knowm.xchange.service.BaseParamsDigest;
import org.knowm.xchange.utils.DigestUtils;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import javax.ws.rs.HeaderParam;

public class CoincheckDigest extends BaseParamsDigest {

  /**
   * Constructor
   *
   * @param secretKeyBase64
   * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded key is invalid).
   */
  private CoincheckDigest(String secretKeyBase64) {

    super(secretKeyBase64, HMAC_SHA_256);
  }

  public static CoincheckDigest createInstance(String secretKeyBase64) {

    return secretKeyBase64 == null ? null : new CoincheckDigest(secretKeyBase64);
  }

  public String digestParams(RestInvocation restInvocation) {

    String url = restInvocation.getInvocationUrl();
    String body = restInvocation.getRequestBody();
    String nonce = restInvocation.getParamValue(HeaderParam.class, CoincheckAuthenticated.HEADER_NONCE).toString();

    String msg = nonce + url + body;

    Mac mac = getMac();
    mac.update(msg.toString().getBytes());
    return DigestUtils.bytesToHex(mac.doFinal()).toLowerCase();
  }
}
