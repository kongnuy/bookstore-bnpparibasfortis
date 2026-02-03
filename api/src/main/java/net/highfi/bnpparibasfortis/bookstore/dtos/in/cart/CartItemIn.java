package net.highfi.bnpparibasfortis.bookstore.dtos.in.cart;

import lombok.Data;

@Data
public class CartItemIn {
  private String bookUuid;
  private int quantity;
}
