package net.highfi.bnpparibasfortis.bookstore.entities;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
@EqualsAndHashCode(callSuper = true)
public class Cart extends BaseEntity {

  @Column(name = "FROZEN", nullable = false)
  private boolean frozen;

  @ManyToOne
  @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
  private Account account;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
  private Set<CartItem> items;

  @OneToOne(mappedBy = "cart")
  private Order order;

  @PrePersist
  @PreUpdate
  public void buildRowIndex() {
    super.buildRowIndex();

    if (account != null) {
      this.appendToRowIndex(account.getEmail());
      this.appendToRowIndex(account.getFirstName());
    }

  }
}
