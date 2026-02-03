package net.highfi.bnpparibasfortis.bookstore.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.highfi.bnpparibasfortis.bookstore.constants.ApiErrorMessages;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountUpdateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountFullOut;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountStandardOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Account;
import net.highfi.bnpparibasfortis.bookstore.mappers.IAccountMapper;
import net.highfi.bnpparibasfortis.bookstore.repositories.AccountRepository;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IAccountService;

@Service("accountService")
public class AccountService implements IAccountService {

  private final AccountRepository accountRepository;

  private final IAccountMapper accountMapper;

  public AccountService(
      @Qualifier("accountRepository") final AccountRepository accountRepository,
      @Qualifier("accountMapper") final IAccountMapper accountMapper) {
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
  }

  @Override
  public List<Account> getAll(BaseSearchParams accountSearchParamsIn) {
    return accountRepository.search(accountSearchParamsIn);
  }

  @Override
  public List<AccountStandardOut> findAll(BaseSearchParams accountSearchParamsIn) {
    return accountMapper.toAccountStandardOut(getAll(accountSearchParamsIn));
  }

  @Override
  public AccountFullOut findOne(String identifier) {
    return accountMapper.toAccountFullOut(loadByIdentifier(identifier));
  }

  @Override
  public AccountFullOut create(AccountCreateIn accountCreateIn) {
    var account = accountMapper.fromAccountCreateIn(accountCreateIn);

    return accountMapper.toAccountFullOut(accountRepository.save(account));
  }

  @Override
  public AccountFullOut update(String uuid, AccountUpdateIn accountUpdateIn) {
    var account = accountMapper.fromAccountUpdateIn(this.loadByIdentifier(uuid), accountUpdateIn);

    return accountMapper.toAccountFullOut(accountRepository.save(account));
  }

  @Override
  public Account loadByIdentifier(String identifier) {
    return this.loadByIdentifier(identifier, true);
  }

  @Override
  public Account loadByIdentifier(String identifier, boolean orElseThrow) {
    var account = accountRepository
        .findByUuidOrUserNameOrEmailOrPhoneNumber(identifier, identifier, identifier, identifier).orElse(null);
    if (orElseThrow && account == null) {
      throw new EntityNotFoundException(String.format(ApiErrorMessages.ACCOUNT_NOT_FOUND, identifier));
    }
    return account;
  }

  @Override
  public boolean delete(String identifier) {
    var account = loadByIdentifier(identifier);
    accountRepository.delete(account);
    return true;
  }

}
