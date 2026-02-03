package net.highfi.bnpparibasfortis.bookstore.entities;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.highfi.bnpparibasfortis.bookstore.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

  @Column(name = "STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CART_ID", referencedColumnName = "ID", nullable = false)
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "DELIVERY_ADDRESS_ID", referencedColumnName = "ID", nullable = false)
  private AccountAddress deliveryAddress;

  @PrePersist
  @PreUpdate
  public void buildRowIndex() {
    super.buildRowIndex();

    this.appendToRowIndex(status.name());
  }
}
