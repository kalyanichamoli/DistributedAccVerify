package org.help.wallet;

import java.security.NoSuchAlgorithmException;

public interface WalletGenerator {

  Wallet generate() throws NoSuchAlgorithmException;

}
