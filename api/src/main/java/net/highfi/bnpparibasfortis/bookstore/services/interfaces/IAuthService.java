package net.highfi.bnpparibasfortis.bookstore.services.interfaces;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountLoginIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountFullOut;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountLoginOut;

public interface IAuthService {

  public AccountFullOut register(AccountCreateIn accountCreateIn);

  public AccountLoginOut authenticate(AccountLoginIn input);
}
