package net.highfi.bnpparibasfortis.bookstore.services.interfaces;

import java.util.List;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.book.BookIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.book.BookOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Book;

public interface IBookService {

  public List<Book> getAll(BaseSearchParams baseSearchParams);

  public List<BookOut> findAll(BaseSearchParams baseSearchParams);

  public BookOut create(BookIn bookCreateIn);

  public BookOut update(String uuid, BookIn bookIn);

  public Book loadByIdentifier(String identifier);

  public Book loadByIdentifier(String identifier, boolean orElseThrow);

  public boolean delete(String identifier);

  public BookOut findOne(String identifier);
}
