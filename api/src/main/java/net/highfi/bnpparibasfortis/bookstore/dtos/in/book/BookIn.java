package net.highfi.bnpparibasfortis.bookstore.dtos.in.book;

import lombok.Data;
import net.highfi.bnpparibasfortis.bookstore.enums.BookStatus;

@Data
public class BookIn {
  private String title;
  private String authors;
  private String summary;
  private Double price;
  private String cover;
  private BookStatus status;
}
