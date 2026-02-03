package net.highfi.bnpparibasfortis.bookstore.dtos.out.book;

import java.sql.Timestamp;

import lombok.Data;
import net.highfi.bnpparibasfortis.bookstore.enums.BookStatus;

@Data
public class BookOut {
  private Long id;
  private String uuid;
  private String title;
  private String authors;
  private String summary;
  private Double price;
  private String cover;
  private BookStatus status;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
