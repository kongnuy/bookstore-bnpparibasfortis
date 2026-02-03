package net.highfi.bnpparibasfortis.bookstore.dtos.out.order;

import java.sql.Timestamp;

import lombok.Data;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.account.AccountAddressOut;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.cart.CartOut;
import net.highfi.bnpparibasfortis.bookstore.enums.OrderStatus;

@Data
public class OrderOut {
  private Long id;
  private String uuid;
  private OrderStatus status;
  private CartOut cart;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  private AccountAddressOut deliveryAddress;
}
