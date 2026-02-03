package net.highfi.bnpparibasfortis.bookstore.dtos.in.account;

import lombok.Data;

@Data
public class AccountAddressUpdateIn {
  protected int streetNumber;
  protected String streetExtension;
  protected String streetName;
  protected String postalCode;
  protected String city;
  protected String region;
  protected String country;
}
