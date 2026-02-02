package net.highfi.bnpparibasfortis.bookstore.entities;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.highfi.bnpparibasfortis.bookstore.enums.BookStatus;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {

  @Column(name = "TITLE", nullable = false)
  private String title;

  @Lob
  @Column(name = "AUTHORS", nullable = false)
  private String authors;

  @Lob
  @Column(name = "SUMMARY", nullable = false)
  private String summary;

  @Column(name = "PRICE", nullable = false, scale = 2)
  private Double price;

  @Lob
  @Column(name = "COVER")
  private String cover;

  @Column(name = "STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private BookStatus status;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  private Set<CartItem> items;

  @PrePersist
  @PreUpdate
  public void buildRowIndex() {
    super.buildRowIndex();

    this.appendToRowIndex(String.valueOf(price));
    this.appendToRowIndex(authors);
    this.appendToRowIndex(title);
    this.appendToRowIndex(status.name());
  }
}
