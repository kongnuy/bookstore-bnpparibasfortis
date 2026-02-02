package net.highfi.bnpparibasfortis.bookstore.services.interfaces;

import io.jsonwebtoken.Claims;
import net.highfi.bnpparibasfortis.bookstore.entities.Account;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJWTService {

  public String extractUsername(String token);

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  public String generateToken(Account account);

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

  public boolean isTokenValid(String token, UserDetails userDetails);

  public long getExpirationTime();
}
