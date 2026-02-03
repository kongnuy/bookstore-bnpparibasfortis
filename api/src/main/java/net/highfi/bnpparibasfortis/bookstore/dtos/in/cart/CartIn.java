package net.highfi.bnpparibasfortis.bookstore.dtos.in.cart;

import java.util.Set;

import lombok.Data;

@Data
public class CartIn {
  private String accountUuid;
  private Set<CartItemIn> items;
}
