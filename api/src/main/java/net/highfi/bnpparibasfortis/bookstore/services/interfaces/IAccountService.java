package net.highfi.bnpparibasfortis.bookstore.services.interfaces;

import java.util.List;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountUpdateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountFullOut;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountStandardOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Account;

public interface IAccountService {

  public List<Account> getAll(BaseSearchParams baseSearchParams);

  public List<AccountStandardOut> findAll(BaseSearchParams baseSearchParams);

  public AccountFullOut create(AccountCreateIn accountCreateIn);

  public AccountFullOut update(String uuid, AccountUpdateIn accountUpdateIn);

  public Account loadByIdentifier(String identifier);

  public Account loadByIdentifier(String identifier, boolean orElseThrow);

  public boolean delete(String identifier);

  public AccountFullOut findOne(String identifier);
}
