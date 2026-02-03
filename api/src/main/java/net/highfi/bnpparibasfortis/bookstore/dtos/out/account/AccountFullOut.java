package net.highfi.bnpparibasfortis.bookstore.dtos.out.account;

import java.sql.Timestamp;
import java.util.Set;

import net.highfi.bnpparibasfortis.bookstore.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountFullOut extends AccountStandardOut {

  protected String phoneNumber;
  protected boolean emailValidated;
  protected boolean phoneNumberValidated;
  protected AccountStatus status;
  protected Timestamp updatedAt;

  protected Set<AccountAddressOut> addresses;
}
