package net.highfi.bnpparibasfortis.bookstore.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum OrderStatus {
  PAID,
  WAITING_PAYMENT,
  PROCESSING,
  DELIVERY_IN_PROGRESS,
  DONE,
  CANCELLED;

  public static List<String> getCodes() {
    return Arrays.stream(values()).map(OrderStatus::name).collect(Collectors.toList());
  }

  public static List<OrderStatus> getValues() {
    return Arrays.stream(values()).collect(Collectors.toList());
  }

  public static OrderStatus getByName(String name) {
    return getValues().stream().filter(e -> e.name().equals(name)).findFirst().orElse(null);
  }
}
