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
import net.highfi.bnpparibasfortis.bookstore.dtos.in.book.BookIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.shared.GenericApiResponse;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IBookService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(Constants.API_BOOK_PREFIX)
public class BookController {

  private final IBookService bookService;

  public BookController(@Qualifier("bookService") final IBookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> findAll(
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) Integer offset,
      @RequestParam(required = false) String search) {
    return new GenericApiResponse<>(bookService.findAll(BaseSearchParams.fromFindAll(offset, limit, search)));
  }

  @GetMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> findOne(@PathVariable(name = "identifier") String identifier) {
    return new GenericApiResponse<>(bookService.findOne(identifier));
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> create(@Validated @RequestBody BookIn bookCreateIn) {
    return new GenericApiResponse<>(bookService.create(bookCreateIn));
  }

  @PutMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> update(@PathVariable(name = "identifier") String identifier,
      @Validated @RequestBody BookIn bookUpdateIn) {
    return new GenericApiResponse<>(bookService.update(identifier, bookUpdateIn));
  }

  @DeleteMapping(path = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericApiResponse<?> delete(@PathVariable(name = "identifier") String identifier) {
    return new GenericApiResponse<>(bookService.delete(identifier));
  }
}
