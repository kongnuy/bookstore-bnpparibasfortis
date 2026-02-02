package net.highfi.bnpparibasfortis.bookstore.dtos.out.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountLoginOut extends AccountFullOut {

  private String jwtToken;

  private long jwtTokenExpiresIn;

}
