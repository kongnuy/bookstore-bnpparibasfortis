package net.highfi.bnpparibasfortis.bookstore.services.interfaces;

import java.util.List;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.order.OrderCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.order.OrderUpdateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.order.OrderOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Order;
import net.highfi.bnpparibasfortis.bookstore.exceptions.RequestValidationException;

public interface IOrderService {

  public List<Order> getAll(BaseSearchParams baseSearchParams);

  public List<OrderOut> findAll(BaseSearchParams baseSearchParams);

  public OrderOut create(OrderCreateIn cartIn) throws RequestValidationException;

  public OrderOut update(String uuid, OrderUpdateIn cartIn);

  public Order loadByIdentifier(String identifier);

  public Order loadByIdentifier(String identifier, boolean orElseThrow);

  public boolean delete(String identifier);

  public OrderOut findOne(String identifier);
}
