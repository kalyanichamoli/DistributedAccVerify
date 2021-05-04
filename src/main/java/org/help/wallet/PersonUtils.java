package org.help.wallet;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PersonUtils {


  private static final RsaWalletGenerator walletGenerator = new RsaWalletGenerator();
  private static final RSASignature rsaSignature = new RSASignature();
  private static final Random random = new Random();
  private static List<String> roles = Arrays.asList("Oxygen", "Medicine", "Medical Equipments", "Ventilators", "Ambulance", "Concentrators");
  private static List<String> cities = Arrays.asList("Delhi", "Noida", "Banglore", "Faridabad", "Ghaziyabad", "Mumbai");

  public static Volunteer volunteer(String name, String identifier, String validationUrl) {
    Volunteer volunteer = new Volunteer();
    volunteer.setName(name);
    volunteer.setWallet(walletGenerator.generate());
    volunteer.setIdentifier(identifier);
    volunteer.setValidationUrl(validationUrl);
    volunteer.setCertifiedForRoles(roles(2));
    volunteer.setCity(cities.get(random.nextInt(cities.size() - 1)));
    return volunteer;
  }

  public static void certify(Volunteer personToCertify, Volunteer certifiedBy) {
    //Say some document validation has been performed here.
    personToCertify.setSignature(rsaSignature.generate(personToCertify.validatedInfo().getBytes(), certifiedBy.getWallet()));
    personToCertify.setSignatureKey(certifiedBy.getWallet().getPublicKey());
    List<SignatureAuthority> signatureAuthorities = new ArrayList<>();
    signatureAuthorities.add(new SignatureAuthority(certifiedBy.getName() + "_" + certifiedBy.getIdentifier(), certifiedBy.getValidationUrl(),
        certifiedBy.getWallet().getPublicKey()));
    signatureAuthorities.addAll(certifiedBy.getTrustAuthorities());
    personToCertify.addAuthority(signatureAuthorities);
  }

  public static boolean validateVolunteerClaim(Volunteer volunteerToValidated) {
    return rsaSignature.validate(volunteerToValidated.getSignature(), volunteerToValidated.getSignatureKey(), volunteerToValidated.validatedInfo().getBytes());
  }

  private static List<String> roles(int count) {
    return IntStream.range(0, count).mapToObj(i -> roles.get(random.nextInt(roles.size() - 1))).collect(toList());
  }
}
