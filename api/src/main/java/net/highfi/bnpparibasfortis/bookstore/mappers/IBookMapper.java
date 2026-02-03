package net.highfi.bnpparibasfortis.bookstore.mappers;

import java.util.List;

import org.mapstruct.AnnotateWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.AnnotateWith.Element;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import net.highfi.bnpparibasfortis.bookstore.dtos.in.book.BookIn;
import net.highfi.bnpparibasfortis.bookstore.dtos.out.book.BookOut;
import net.highfi.bnpparibasfortis.bookstore.entities.Book;

@Mapper(implementationName = "BookMapperImpl", componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
@AnnotateWith(value = Service.class, elements = @Element(strings = "bookMapper"))
public interface IBookMapper {

  Book fromBookIn(BookIn bookIn);

  Book fromBookIn(@MappingTarget Book book, BookIn bookIn);

  BookOut toBookOut(Book book);

  List<BookOut> toBookOut(List<Book> book);
}
