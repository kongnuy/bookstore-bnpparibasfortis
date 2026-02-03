package net.highfi.bnpparibasfortis.bookstore.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.AnnotateWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.AnnotateWith.Element;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountUpdateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountFullOut;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountLoginOut;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountStandardOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Account;
import net.highfi.bnpparibasfortis.bookstore.enums.AccountRole;
import net.highfi.bnpparibasfortis.bookstore.enums.AccountStatus;

@Mapper(implementationName = "AccountMapperImpl", componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
@AnnotateWith(value = Service.class, elements = @Element(strings = "accountMapper"))
public interface IAccountMapper {

  @Mapping(target = "emailValidated", expression = "java(true)")
  @Mapping(target = "phoneNumberValidated", expression = "java(true)")
  Account fromAccountCreateIn(AccountCreateIn accountCreateIn);

  Account fromAccountUpdateIn(@MappingTarget Account account, AccountUpdateIn accountUpdateIn);

  @AfterMapping
  default void fromAccountCreateIn_AfterMapping(@MappingTarget Account account) {
    if (account.getStatus() == null) {
      account.setStatus(AccountStatus.ACTIVE);
    }
    if (account.getRole() == null) {
      account.setRole(AccountRole.USER);
    }
  }

  AccountFullOut toAccountFullOut(Account account);

  @Mapping(target = "jwtToken", source = "jwtToken")
  @Mapping(target = "jwtTokenExpiresIn", source = "jwtTokenExpiresIn")
  AccountLoginOut toAccountLoginOut(Account account, String jwtToken, long jwtTokenExpiresIn);

  AccountStandardOut toAccountStandardOut(Account account);

  List<AccountStandardOut> toAccountStandardOut(List<Account> account);
}
