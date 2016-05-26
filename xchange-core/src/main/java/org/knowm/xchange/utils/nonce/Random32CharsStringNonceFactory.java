package org.knowm.xchange.utils.nonce;

import si.mazi.rescu.SynchronizedValueFactory;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 12/08/15
 * Time: 12:56
 */
public class Random32CharsStringNonceFactory implements SynchronizedValueFactory<String> {

  private final Random random = new Random();
  private final char[] nonceBuffer = new char[32];
  private static final char[] symbols;

  static {
    StringBuilder tmp = new StringBuilder();
    for (char ch = '0'; ch <= '9'; ++ch)
      tmp.append(ch);
    for (char ch = 'a'; ch <= 'z'; ++ch)
      tmp.append(ch);
    symbols = tmp.toString().toCharArray();
  }

  @Override
  public String createValue() {
      for (int idx = 0; idx < nonceBuffer.length; ++idx)
        nonceBuffer[idx] = symbols[random.nextInt(symbols.length)];
      return new String(nonceBuffer);
  }
}
