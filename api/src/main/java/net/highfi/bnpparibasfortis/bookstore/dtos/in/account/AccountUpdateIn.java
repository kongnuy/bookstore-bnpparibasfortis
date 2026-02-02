package net.highfi.bnpparibasfortis.bookstore.dtos.in.account;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountUpdateIn {
  private String firstName;
  private String lastName;
  private String username;
  private LocalDate birthDate;
}
