package net.highfi.bnpparibasfortis.bookstore.dtos.out.cart;

import java.sql.Timestamp;
import java.util.Set;

import lombok.Data;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountStandardOut;

@Data
public class CartOut {
  private Long id;
  private String uuid;
  private AccountStandardOut account;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  private Set<CartItemOut> items;
}
