package net.highfi.bnpparibasfortis.bookstore.services.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.highfi.bnpparibasfortis.bookstore.constants.ApiErrorMessages;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.cart.CartIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.cart.CartItemIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.cart.CartOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Cart;
import net.highfi.bnpparibasfortis.bookstore.entities.CartItem;
import net.highfi.bnpparibasfortis.bookstore.mappers.ICartMapper;
import net.highfi.bnpparibasfortis.bookstore.repositories.CartRepository;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IAccountService;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IBookService;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.ICartService;

@Service("cartService")
public class CartService implements ICartService {

  private final CartRepository cartRepository;

  private final ICartMapper cartMapper;

  private final IAccountService accountService;

  private final IBookService bookService;

  public CartService(
      @Qualifier("cartRepository") final CartRepository cartRepository,
      @Qualifier("cartMapper") final ICartMapper cartMapper,
      @Qualifier("accountService") final IAccountService accountService,
      @Qualifier("bookService") final IBookService bookService) {
    this.cartRepository = cartRepository;
    this.cartMapper = cartMapper;
    this.accountService = accountService;
    this.bookService = bookService;
  }

  @Override
  public CartOut findOne(String identifier) {
    return cartMapper.toCartOut(loadByIdentifier(identifier));
  }

  @Override
  public CartOut create(CartIn cartCreateIn) {
    var cart = new Cart();
    cart.setAccount(accountService.loadByIdentifier(cartCreateIn.getAccountUuid()));
    cartCreateIn.getItems().forEach(item -> {
      var itemEntity = new CartItem();
      itemEntity.setBook(bookService.loadByIdentifier(item.getBookUuid()));
      itemEntity.setQuantity(item.getQuantity());

      cart.getItems().add(itemEntity);
    });

    return cartMapper.toCartOut(cartRepository.save(cart));
  }

  @Override
  public Cart loadByIdentifier(String identifier) {
    return this.loadByIdentifier(identifier, true);
  }

  @Override
  public Cart loadByIdentifier(String identifier, boolean orElseThrow) {
    var cart = cartRepository
        .findByUuid(identifier).orElse(null);
    if (orElseThrow && cart == null) {
      throw new EntityNotFoundException(String.format(ApiErrorMessages.ACCOUNT_NOT_FOUND, identifier));
    }
    return cart;
  }

  @Override
  public boolean delete(String identifier) {
    var cart = loadByIdentifier(identifier);
    cartRepository.delete(cart);
    return true;
  }

  @Override
  public CartOut updateItem(String cartIdentifier, CartItemIn cartItemIn) {
    var cart = loadByIdentifier(cartIdentifier);
    cart.getItems().removeIf(item -> item.getBook().getUuid().equals(cartItemIn.getBookUuid()));

    if (cartItemIn.getQuantity() > 0) {
      var itemEntity = new CartItem();
      itemEntity.setBook(bookService.loadByIdentifier(cartItemIn.getBookUuid()));
      itemEntity.setQuantity(cartItemIn.getQuantity());

      cart.getItems().add(itemEntity);
    }

    return cartMapper.toCartOut(cartRepository.save(cart));
  }

}
