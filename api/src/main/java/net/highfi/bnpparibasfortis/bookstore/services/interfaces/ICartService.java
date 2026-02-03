package net.highfi.bnpparibasfortis.bookstore.services.interfaces;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.cart.CartIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.cart.CartItemIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.cart.CartOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Cart;

public interface ICartService {

  public CartOut create(CartIn cartIn);

  public CartOut updateItem(String cartIdentifier, CartItemIn cartItemIn);

  public Cart loadByIdentifier(String identifier);

  public Cart loadByIdentifier(String identifier, boolean orElseThrow);

  public boolean delete(String identifier);

  public CartOut findOne(String identifier);
}
