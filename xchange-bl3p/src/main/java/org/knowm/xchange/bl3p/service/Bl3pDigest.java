package org.knowm.xchange.bl3p.service;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;
import si.mazi.rescu.utils.Base64;

import javax.crypto.Mac;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 17:12
 */
public class Bl3pDigest extends BaseParamsDigest {

  public static Bl3pDigest createInstance(String secretKeyBase64) {

    if (secretKeyBase64 == null || secretKeyBase64.isEmpty()) {
      return null;
    }

    try {
      byte[] decode = Base64.decode(secretKeyBase64);
      return new Bl3pDigest(decode);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


  protected Bl3pDigest(byte[] decodeSecretKey) throws IllegalArgumentException {

    super(decodeSecretKey, HMAC_SHA_512);
  }


  /*
  base64 encode of (
    HMAC_SHA512 of (
        $path_of_request + null terminator + $post_data_string
    ) with the key set to the base64 decode of the private apikey)
  )
   */

  @Override
  public String digestParams(RestInvocation restInvocation) {

    String path = restInvocation.getMethodPath();
    String requestBody = restInvocation.getRequestBody();

    StringBuilder msg = new StringBuilder()
        .append(path)
        .append('\0')
        .append(requestBody);

    Mac mac512 = getMac();
    mac512.update(msg.toString().getBytes());

    String encode = Base64.encodeBytes(mac512.doFinal()).trim();

    return encode;
  }
}
