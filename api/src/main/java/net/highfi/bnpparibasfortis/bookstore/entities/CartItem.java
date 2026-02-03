package net.highfi.bnpparibasfortis.bookstore.entities;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")
@EqualsAndHashCode(callSuper = true)
public class CartItem extends BaseEntity {

  @Column(name = "QUANTITY", nullable = false)
  private int quantity;

  @ManyToOne
  @JoinColumn(name = "CART_ID", referencedColumnName = "ID", nullable = false)
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID", nullable = false)
  private Book book;

  @PrePersist
  @PreUpdate
  public void buildRowIndex() {
    super.buildRowIndex();

    this.appendToRowIndex(String.valueOf(quantity));
  }
}
