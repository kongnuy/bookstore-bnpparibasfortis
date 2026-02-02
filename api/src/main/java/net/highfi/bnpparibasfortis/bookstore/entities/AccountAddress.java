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
@Table(name = "account_addresses")
@EqualsAndHashCode(callSuper = true)
public class AccountAddress extends BaseEntity {

  @Column(name = "STREET_NUMBER", nullable = false)
  private int streetNumber;

  @Column(name = "STREET_EXTENSION")
  private String streetExtension;

  @Column(name = "STREET_NAME", nullable = false)
  private String streetName;

  @Column(name = "POSTAL_CODE", nullable = false)
  private String postalCode;

  @Column(name = "CITY", nullable = false)
  private String city;

  @Column(name = "REGION")
  private String region;

  @Column(name = "COUNTRY", nullable = false)
  private String country;

  @ManyToOne
  @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
  private Account account;

  @PrePersist
  @PreUpdate
  public void buildRowIndex() {
    super.buildRowIndex();

    this.appendToRowIndex(String.valueOf(streetNumber));
    this.appendToRowIndex(streetExtension);
    this.appendToRowIndex(streetName);
    this.appendToRowIndex(postalCode);
    this.appendToRowIndex(city);
    this.appendToRowIndex(region);
    this.appendToRowIndex(country);
  }
}
