package net.highfi.bnpparibasfortis.bookstore.dtos.in.account;

import lombok.Data;

@Data
public class AccountLoginIn {
  private String identifier;
  private String password;
}
