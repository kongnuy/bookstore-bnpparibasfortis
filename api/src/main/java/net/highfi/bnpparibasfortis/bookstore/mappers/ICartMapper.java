package net.highfi.bnpparibasfortis.bookstore.mappers;

import java.util.List;

import org.mapstruct.AnnotateWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.AnnotateWith.Element;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import net.highfi.bnpparibasfortis.bookstore.dtos.out.cart.CartItemOut;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.cart.CartOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Cart;
import net.highfi.bnpparibasfortis.bookstore.entities.CartItem;

@Mapper(implementationName = "CartMapperImpl", componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
@AnnotateWith(value = Service.class, elements = @Element(strings = "cartMapper"))
public interface ICartMapper {

  CartOut toCartOut(Cart cart);

  @Mapping(target = "bookTitle", source = "book.title")
  @Mapping(target = "bookAuthors", source = "book.authors")
  @Mapping(target = "bookCover", source = "book.cover")
  @Mapping(target = "bookPrice", source = "book.price")
  CartItemOut toCartItemOut(CartItem cart);

  List<CartOut> toCartOut(List<Cart> cart);
}
