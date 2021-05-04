package org.help.wallet;

import java.io.ByteArrayInputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSASignature {

  private Base64.Encoder encoder = Base64.getEncoder();
  private Base64.Decoder decoder = Base64.getDecoder();

  public String generate(byte[] data, Wallet wallet) {
    try {
      final Signature sign = Signature.getInstance("SHA256withRSA");
      sign.initSign(privateKey(decoder.decode(wallet.getPrivateKey())));
      byte[] buf = new byte[2048];
      ByteArrayInputStream in = new ByteArrayInputStream(data);
      int len;
      while ((len = in.read(buf)) != -1) {
        sign.update(buf, 0, len);
      }
      byte[] signature = sign.sign();
      return encoder.encodeToString(signature);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public boolean validate(String signature, String publicKey, byte[] data) {
    try {
      final Signature sign = Signature.getInstance("SHA256withRSA");
      sign.initVerify(publicKey(decoder.decode(publicKey)));
      byte[] buf = new byte[2048];
      ByteArrayInputStream in = new ByteArrayInputStream(data);
      int len;
      while ((len = in.read(buf)) != -1) {
        sign.update(buf, 0, len);
      }
      return sign.verify(decoder.decode(signature));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private PrivateKey privateKey(byte[] data) throws InvalidKeySpecException, NoSuchAlgorithmException {
    PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(data);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePrivate(ks);
  }

  private PublicKey publicKey(byte[] data) throws NoSuchAlgorithmException, InvalidKeySpecException {
    X509EncodedKeySpec ks = new X509EncodedKeySpec(data);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(ks);
  }
}
