package net.highfi.bnpparibasfortis.bookstore.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BookStatus {
  DRAFT,
  PUBLISHED,
  DELETED,
  UNPUBLISHED;

  public static List<String> getCodes() {
    return Arrays.stream(values()).map(BookStatus::name).collect(Collectors.toList());
  }

  public static List<BookStatus> getValues() {
    return Arrays.stream(values()).collect(Collectors.toList());
  }

  public static BookStatus getByName(String name) {
    return getValues().stream().filter(e -> e.name().equals(name)).findFirst().orElse(null);
  }
}
