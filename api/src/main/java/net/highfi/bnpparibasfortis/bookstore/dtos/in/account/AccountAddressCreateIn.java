package net.highfi.bnpparibasfortis.bookstore.dtos.in.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountAddressCreateIn extends AccountAddressUpdateIn {
  private String accountUuid;

}
