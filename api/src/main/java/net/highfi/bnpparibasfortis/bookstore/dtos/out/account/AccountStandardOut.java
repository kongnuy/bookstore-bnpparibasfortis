package net.highfi.bnpparibasfortis.bookstore.dtos.out.account;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStandardOut {

  protected Long id;
  protected String uuid;
  protected String firstName;
  protected String lastName;
  protected String email;
  protected String username;
  protected LocalDate birthDate;
  protected Timestamp createdAt;

}
