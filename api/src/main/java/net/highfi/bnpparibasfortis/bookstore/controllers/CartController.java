package net.highfi.bnpparibasfortis.bookstore.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.highfi.bnpparibasfortis.bookstore.constants.Constants;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.cart.CartIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.cart.CartItemIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.shared.GenericApiResponse;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.ICartService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(Constants.API_ACCOUNT_PREFIX)
public class CartController {

  private final ICartService cartService;

  public CartController(@Qualifier("cartService") final ICartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> findOne(@PathVariable(name = "identifier") String identifier) {
    return new GenericApiResponse<>(cartService.findOne(identifier));
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> create(@Validated @RequestBody CartIn cartCreateIn) {
    return new GenericApiResponse<>(cartService.create(cartCreateIn));
  }

  @PostMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> updateCart(@PathVariable(name = "identifier") String identifier,
      @Validated @RequestBody CartItemIn cartItemIn) {
    return new GenericApiResponse<>(cartService.updateItem(identifier, cartItemIn));
  }

  @DeleteMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> delete(@PathVariable(name = "identifier") String identifier) {
    return new GenericApiResponse<>(cartService.delete(identifier));
  }
}
