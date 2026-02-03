package net.highfi.bnpparibasfortis.bookstore.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.highfi.bnpparibasfortis.bookstore.constants.ApiErrorMessages;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.book.BookIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.book.BookOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Book;
import net.highfi.bnpparibasfortis.bookstore.mappers.IBookMapper;
import net.highfi.bnpparibasfortis.bookstore.repositories.BookRepository;
import net.highfi.bnpparibasfortis.bookstore.services.interfaces.IBookService;

@Service("bookService")
public class BookService implements IBookService {

  private final BookRepository bookRepository;

  private final IBookMapper bookMapper;

  public BookService(
      @Qualifier("bookRepository") final BookRepository bookRepository,
      @Qualifier("bookMapper") final IBookMapper bookMapper) {
    this.bookRepository = bookRepository;
    this.bookMapper = bookMapper;
  }

  @Override
  public List<Book> getAll(BaseSearchParams bookSearchParamsIn) {
    return bookRepository.search(bookSearchParamsIn);
  }

  @Override
  public List<BookOut> findAll(BaseSearchParams bookSearchParamsIn) {
    return bookMapper.toBookOut(getAll(bookSearchParamsIn));
  }

  @Override
  public BookOut findOne(String identifier) {
    return bookMapper.toBookOut(loadByIdentifier(identifier));
  }

  @Override
  public BookOut create(BookIn bookCreateIn) {
    var book = bookMapper.fromBookIn(bookCreateIn);

    return bookMapper.toBookOut(bookRepository.save(book));
  }

  @Override
  public BookOut update(String uuid, BookIn bookUpdateIn) {
    var book = bookMapper.fromBookIn(this.loadByIdentifier(uuid), bookUpdateIn);

    return bookMapper.toBookOut(bookRepository.save(book));
  }

  @Override
  public Book loadByIdentifier(String identifier) {
    return this.loadByIdentifier(identifier, true);
  }

  @Override
  public Book loadByIdentifier(String identifier, boolean orElseThrow) {
    var book = bookRepository
        .findByUuid(identifier).orElse(null);
    if (orElseThrow && book == null) {
      throw new EntityNotFoundException(String.format(ApiErrorMessages.ACCOUNT_NOT_FOUND, identifier));
    }
    return book;
  }

  @Override
  public boolean delete(String identifier) {
    var book = loadByIdentifier(identifier);
    bookRepository.delete(book);
    return true;
  }

}
