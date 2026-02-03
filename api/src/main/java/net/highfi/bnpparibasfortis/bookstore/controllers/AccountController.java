package net.highfi.bnpparibasfortis.bookstore.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.highfi.bnpparibasfortis.bookstore.constants.Constants;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountUpdateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
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

  public GenericApiResponse<?> findAll(
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) Integer offset,
      @RequestParam(required = false) String search) {
    return new GenericApiResponse<>(accountService.findAll(BaseSearchParams.fromFindAll(offset, limit, search)));
  }

  @GetMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> findOne(@PathVariable(name = "identifier") String identifier) {
    return new GenericApiResponse<>(accountService.findOne(identifier));
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> create(@Validated @RequestBody AccountCreateIn accountCreateIn) {
    return new GenericApiResponse<>(accountService.create(accountCreateIn));
  }

  @PutMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> update(@PathVariable(name = "identifier") String identifier,
      @Validated @RequestBody AccountUpdateIn accountUpdateIn) {
    return new GenericApiResponse<>(accountService.update(identifier, accountUpdateIn));
  }

  @DeleteMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> delete(@PathVariable(name = "identifier") String identifier) {
    return new GenericApiResponse<>(accountService.delete(identifier));
  }
}
