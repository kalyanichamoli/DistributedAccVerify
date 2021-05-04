package org.help.wallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WalletUserApp {

  private static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
  private static Random random = new Random();

  public static void main(String[] args) throws Exception {
    new WalletUserApp().demo();
  }

  public void demo() throws JsonProcessingException {
    Volunteer imaCertifiedMember = PersonUtils
        .volunteer("Siddharth", "Certified Member of IMA, New Delhi, Demo", "https://url.to.my.public.key.gov.in/volunteer/publickey");
    Volunteer ngoCoFounder = PersonUtils.volunteer("Bhuvan", "Co-Founder, XYZ NGO, New Delhi", "https://XYZ.NGO.org/volunteer/publickey");
    PersonUtils.certify(ngoCoFounder, imaCertifiedMember);
    System.out.println(mapper.writeValueAsString(ngoCoFounder));
    Faker faker = new Faker();
    List<Volunteer> volunteerList = IntStream.range(0, 10).mapToObj(i -> PersonUtils
        .volunteer(faker.name().name(), "Member ,XYZ NGO, New Delhi", "")).collect(Collectors.toList());
    volunteerList.forEach(volunteer -> PersonUtils.certify(volunteer, ngoCoFounder));
    System.out.println(mapper.writeValueAsString(volunteerList));

    //Do some basic validation simulation
    Person person = new Person("Common Person", "Looking for help");
    boolean validationResult = PersonUtils.validateVolunteerClaim(volunteerList.get(0));
    System.out.println("Validation result "+ validationResult);

    // IF you don't believe on cryptography, try messing it above data to get validation true for fake data.

  }




}
