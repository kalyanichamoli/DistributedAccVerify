package org.help.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignatureAuthority {
  private String identity;
  private String validationUrl;
  private String publicKey;
}
