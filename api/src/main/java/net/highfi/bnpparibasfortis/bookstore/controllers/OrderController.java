package net.highfi.bnpparibasfortis.bookstore.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.highfi.bnpparibasfortis.bookstore.constants.Constants;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.order.OrderCreateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.order.OrderUpdateIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.shared.GenericApiResponse;
import net.highfi.bnpparibasfortis.bookstore.exceptions.RequestValidationException;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IOrderService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(Constants.API_ACCOUNT_PREFIX)
public class OrderController {

  private final IOrderService orderService;

  public OrderController(@Qualifier("orderService") final IOrderService orderService) {
    this.orderService = orderService;
  }

  public GenericApiResponse<?> findAll(
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) Integer offset,
      @RequestParam(required = false) String search) {
    return new GenericApiResponse<>(orderService.findAll(BaseSearchParams.fromFindAll(offset, limit, search)));
  }

  @GetMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> findOne(@PathVariable(name = "identifier") String identifier) {
    return new GenericApiResponse<>(orderService.findOne(identifier));
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> create(@Validated @RequestBody OrderCreateIn orderCreateIn)
      throws RequestValidationException {
    return new GenericApiResponse<>(orderService.create(orderCreateIn));
  }

  @PutMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> update(@PathVariable(name = "identifier") String identifier,
      @Validated @RequestBody OrderUpdateIn orderUpdateIn) {
    return new GenericApiResponse<>(orderService.update(identifier, orderUpdateIn));
  }

  @DeleteMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> delete(@PathVariable(name = "identifier") String identifier) {
    return new GenericApiResponse<>(orderService.delete(identifier));
  }
}
