package net.highfi.bnpparibasfortis.bookstore.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.highfi.bnpparibasfortis.bookstore.constants.ApiErrorMessages;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.order.OrderCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.order.OrderUpdateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.order.OrderOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Order;
import net.highfi.bnpparibasfortis.bookstore.exceptions.RequestValidationException;
import net.highfi.bnpparibasfortis.bookstore.mappers.IOrderMapper;
import net.highfi.bnpparibasfortis.bookstore.repositories.AccountAddressRepository;
import net.highfi.bnpparibasfortis.bookstore.repositories.CartRepository;
import net.highfi.bnpparibasfortis.bookstore.repositories.OrderRepository;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IOrderService;

@Service("orderService")
public class OrderService implements IOrderService {

  private final OrderRepository orderRepository;

  private final CartRepository cartRepository;

  private final AccountAddressRepository accountAddressRepository;

  private final IOrderMapper orderMapper;

  public OrderService(
      @Qualifier("accountAddressRepository") final AccountAddressRepository accountAddressRepository,
      @Qualifier("cartRepository") final CartRepository cartRepository,
      @Qualifier("orderRepository") final OrderRepository orderRepository,
      @Qualifier("orderMapper") final IOrderMapper orderMapper) {
    this.orderRepository = orderRepository;
    this.orderMapper = orderMapper;
    this.cartRepository = cartRepository;
    this.accountAddressRepository = accountAddressRepository;
  }

  @Override
  public List<Order> getAll(BaseSearchParams orderSearchParamsIn) {
    return orderRepository.search(orderSearchParamsIn);
  }

  @Override
  public List<OrderOut> findAll(BaseSearchParams orderSearchParamsIn) {
    return orderMapper.toOrderOut(getAll(orderSearchParamsIn));
  }

  @Override
  public OrderOut findOne(String identifier) {
    return orderMapper.toOrderOut(loadByIdentifier(identifier));
  }

  @Override
  public OrderOut create(OrderCreateIn orderCreateIn) throws RequestValidationException {
    var order = orderMapper.fromOrderCreateIn(orderCreateIn);
    var cart = cartRepository.findByUuid(orderCreateIn.getCartUuid()).orElseThrow();
    if (cart.isFrozen()) {
      throw new RequestValidationException(String.format(ApiErrorMessages.ACCOUNT_NOT_FOUND, cart.getUuid()));
    }
    order.setCart(cart);
    order.setDeliveryAddress(accountAddressRepository.findByUuid(orderCreateIn.getAddressUuid()).orElseThrow());
    orderRepository.save(order);

    cart.setFrozen(true);
    cartRepository.save(cart);

    return orderMapper.toOrderOut(order);
  }

  @Override
  public OrderOut update(String uuid, OrderUpdateIn orderUpdateIn) {
    var order = orderMapper.fromOrderUpdateIn(this.loadByIdentifier(uuid), orderUpdateIn);
    order.setDeliveryAddress(accountAddressRepository.findByUuid(orderUpdateIn.getAddressUuid()).orElseThrow());

    return orderMapper.toOrderOut(orderRepository.save(order));
  }

  @Override
  public Order loadByIdentifier(String identifier) {
    return this.loadByIdentifier(identifier, true);
  }

  @Override
  public Order loadByIdentifier(String identifier, boolean orElseThrow) {
    var order = orderRepository
        .findByUuid(identifier).orElse(null);
    if (orElseThrow && order == null) {
      throw new EntityNotFoundException(String.format(ApiErrorMessages.ACCOUNT_NOT_FOUND, identifier));
    }
    return order;
  }

  @Override
  public boolean delete(String identifier) {
    var order = loadByIdentifier(identifier);
    orderRepository.delete(order);
    return true;
  }

}
