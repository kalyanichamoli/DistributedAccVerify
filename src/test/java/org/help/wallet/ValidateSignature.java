package org.help.wallet;

import org.junit.Test;

public class ValidateSignature {
  private byte [] data = "some data".getBytes();

  @Test
  public void validateSignature() throws Exception {
    final RsaWalletGenerator generator = new RsaWalletGenerator();
    final Wallet wallet = generator.generate();
    final RSASignature signature = new RSASignature();
    final String generatedSignature = signature.generate(data, wallet);
    System.out.println("Generated signature "+ generatedSignature);
    System.out.println("Validate signature" + signature.validate(generatedSignature, wallet.getPublicKey(), data));
  }

}
