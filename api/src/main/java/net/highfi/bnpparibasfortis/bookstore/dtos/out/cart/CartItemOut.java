package net.highfi.bnpparibasfortis.bookstore.dtos.out.cart;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CartItemOut {
  private Long id;
  private String uuid;
  private int quantity;

  private String bookTitle;
  private String bookAuthors;
  private String bookCover;
  private Double bookPrice;

  private Timestamp createdAt;
  private Timestamp updatedAt;
}
