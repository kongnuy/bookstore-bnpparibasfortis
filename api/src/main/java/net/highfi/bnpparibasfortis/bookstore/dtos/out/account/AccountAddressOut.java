package net.highfi.bnpparibasfortis.bookstore.dtos.out.account;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AccountAddressOut {
  protected Long id;
  protected String uuid;
  protected int streetNumber;
  protected String streetExtension;
  protected String streetName;
  protected String postalCode;
  protected String city;
  protected String region;
  protected String country;
  protected Timestamp createdAt;
  protected Timestamp updatedAt;
}
