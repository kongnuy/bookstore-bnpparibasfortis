package net.highfi.bnpparibasfortis.bookstore.services.interfaces;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountFullOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Account;

public interface IAccountService {

  public AccountFullOut create(AccountCreateIn accountCreateIn);

  public Account loadByIdentifier(String identifier);

  public Account loadByIdentifier(String identifier, boolean orElseThrow);
}
