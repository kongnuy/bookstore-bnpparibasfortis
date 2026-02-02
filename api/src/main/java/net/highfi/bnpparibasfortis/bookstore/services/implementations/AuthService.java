package net.highfi.bnpparibasfortis.bookstore.services.implementations;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.account.AccountLoginIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountFullOut;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountLoginOut;
import net.highfi.bnpparibasfortis.bookstore.mappers.IAccountMapper;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IAccountService;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IAuthService;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IJWTService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthService implements IAuthService {

  private final IAccountMapper accountMapper;

  private final IJWTService jwtService;

  private final IAccountService accountService;

  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  public AuthService(@Qualifier("jwtService") JWTService jwtService, IAccountService accountService,
      IAccountMapper accountMapper,
      AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.accountService = accountService;
    this.accountMapper = accountMapper;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @Override
  public AccountFullOut register(AccountCreateIn accountCreateIn) {
    accountCreateIn.setPassword(passwordEncoder.encode(accountCreateIn.getPassword()));
    return accountService.create(accountCreateIn);
  }

  @Override
  public AccountLoginOut authenticate(AccountLoginIn input) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            input.getIdentifier(),
            input.getPassword()));

    var account = accountService.loadByIdentifier(input.getIdentifier());
    String jwtToken = jwtService.generateToken(account);

    return accountMapper.toAccountLoginOut(account, jwtToken, jwtService.getExpirationTime());
  }
}
