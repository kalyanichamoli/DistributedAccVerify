package org.help.wallet;

import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import org.junit.Test;

public class RsaWalletGeneratorTest {

  @Test
  public void generate() throws NoSuchAlgorithmException {
    RsaWalletGenerator walletGenerator = new RsaWalletGenerator();
    Wallet wallet = walletGenerator.generate();
    assertNotNull("Wallet can't be null.", wallet);
    assertNotNull("Wallet can't be null.", wallet.getPrivateKey());
    assertNotNull("Wallet can't be null.", wallet.getPublicKey());
    System.out.println(wallet);
  }
}
