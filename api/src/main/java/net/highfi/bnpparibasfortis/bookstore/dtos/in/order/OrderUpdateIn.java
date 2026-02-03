package net.highfi.bnpparibasfortis.bookstore.dtos.in.order;

import lombok.Data;

@Data
public class OrderUpdateIn {
  private String status;
  private String addressUuid;
}
