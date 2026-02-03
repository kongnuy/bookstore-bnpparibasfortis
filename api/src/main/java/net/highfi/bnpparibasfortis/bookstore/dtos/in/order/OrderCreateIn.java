package net.highfi.bnpparibasfortis.bookstore.dtos.in.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreateIn extends OrderUpdateIn {
  private String cartUuid;
}
