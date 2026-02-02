package net.highfi.bnpparibasfortis.bookstore.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.highfi.bnpparibasfortis.bookstore.constants.Constants;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.shared.GenericApiResponse;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IAccountService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(Constants.API_ACCOUNT_PREFIX)
public class AccountController {

  private final IAccountService accountService;

  public AccountController(@Qualifier("accountService") final IAccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> create(@Validated @RequestBody AccountCreateIn accountCreateIn) {
    return new GenericApiResponse<>(accountService.create(accountCreateIn));
  }
}
