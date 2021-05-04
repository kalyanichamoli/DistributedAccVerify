package org.help.wallet;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class RsaWalletGenerator implements WalletGenerator {

  private Base64.Encoder encoder = Base64.getEncoder();


  public Wallet generate() {
    try {
      KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
      kpg.initialize(2048);
      KeyPair pair = kpg.generateKeyPair();
      return new Wallet(encodeKey(pair.getPublic()), encodeKey(pair.getPrivate()));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  private String encodeKey(Key key) {
    return encoder.encodeToString(key.getEncoded());
  }
}
