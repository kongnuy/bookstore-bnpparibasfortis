package net.highfi.bnpparibasfortis.bookstore.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AccountStatus {
  ACTIVE,
  INACTIVE,
  DELETED,
  DELETE_REQUESTED,
  READY_TO_DELETE,
  SUSPENDED,
  FROZEN;

  public static List<String> getCodes() {
    return Arrays.stream(values()).map(AccountStatus::name).collect(Collectors.toList());
  }

  public static List<AccountStatus> getValues() {
    return Arrays.stream(values()).collect(Collectors.toList());
  }

  public static AccountStatus getByName(String name) {
    return getValues().stream().filter(e -> e.name().equals(name)).findFirst().orElse(null);
  }
}
