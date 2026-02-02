package net.highfi.bnpparibasfortis.bookstore.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.highfi.bnpparibasfortis.bookstore.constants.Constants;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountLoginIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.shared.GenericApiResponse;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IAuthService;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(Constants.API_AUTH_PREFIX)
public class AuthController {

  private final IAuthService authService;

  public AuthController(@Qualifier("authService") final IAuthService authService) {
    this.authService = authService;
  }

  @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> register(@Validated @RequestBody AccountCreateIn accountCreateIn) {
    return new GenericApiResponse<>(authService.register(accountCreateIn));
  }

  @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> authenticate(@Validated @RequestBody AccountLoginIn accountLoginIn) {
    return new GenericApiResponse<>(authService.authenticate(accountLoginIn));
  }
}
