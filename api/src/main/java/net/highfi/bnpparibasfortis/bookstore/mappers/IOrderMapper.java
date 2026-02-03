package net.highfi.bnpparibasfortis.bookstore.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.AnnotateWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.AnnotateWith.Element;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.order.OrderCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.order.OrderUpdateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.order.OrderOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Order;
import net.highfi.bnpparibasfortis.bookstore.enums.OrderStatus;

@Mapper(implementationName = "OrderMapperImpl", componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
@AnnotateWith(value = Service.class, elements = @Element(strings = "orderMapper"))
public interface IOrderMapper {

  Order fromOrderCreateIn(OrderCreateIn orderCreateIn);

  Order fromOrderUpdateIn(@MappingTarget Order order, OrderUpdateIn orderUpdateIn);

  @AfterMapping
  default void fromOrderCreateIn_AfterMapping(@MappingTarget Order order) {
    if (order.getStatus() == null) {
      order.setStatus(OrderStatus.PAID);
    }
  }

  OrderOut toOrderOut(Order order);

  List<OrderOut> toOrderOut(List<Order> order);
}
