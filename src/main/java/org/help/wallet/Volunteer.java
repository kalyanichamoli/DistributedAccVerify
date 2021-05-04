package org.help.wallet;

import java.util.ArrayList;
import java.util.List;

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
public class Volunteer extends Person {
  private Wallet wallet;
  private String validationUrl;
  private String signature;
  private String signatureKey;
  private List<String> certifiedForRoles;
  private List<SignatureAuthority> trustAuthorities = new ArrayList<>();
  private String city;

  public void addAuthority(List<SignatureAuthority> authorities) {
    trustAuthorities.addAll(authorities);
  }

  public String validatedInfo() {
    return getName() + "-" + getIdentifier() + "-" + getValidationUrl() + "- CITY ["+getCity()+"] Roles ["+getCertifiedForRoles()+"]";
  }

}
