package net.highfi.bnpparibasfortis.bookstore.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AccountRole {
  USER,
  ADMIN;

  public static List<String> getCodes() {
    return Arrays.stream(values()).map(AccountRole::name).collect(Collectors.toList());
  }

  public static List<AccountRole> getValues() {
    return Arrays.stream(values()).collect(Collectors.toList());
  }

  public static AccountRole getByName(String name) {
    return getValues().stream().filter(e -> e.name().equals(name)).findFirst().orElse(null);
  }
}
