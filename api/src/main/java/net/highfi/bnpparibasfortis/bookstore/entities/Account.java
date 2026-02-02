package net.highfi.bnpparibasfortis.bookstore.entities;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.highfi.bnpparibasfortis.bookstore.enums.AccountRole;
import net.highfi.bnpparibasfortis.bookstore.enums.AccountStatus;
import net.highfi.bnpparibasfortis.bookstore.utils.DateUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity implements UserDetails {

  @Column(name = "FIRST_NAME", nullable = false)
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "EMAIL", unique = true, nullable = false)
  private String email;

  @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
  private String phoneNumber;

  @Column(name = "STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountStatus status;

  @Column(name = "EMAIL_VALIDATED", nullable = false)
  private boolean emailValidated;

  @Column(name = "PHONE_NUMBER_VALIDATED", nullable = false)
  private boolean phoneNumberValidated;

  @Column(name = "USER_NAME", unique = true, nullable = false)
  private String username;

  @Column(name = "ROLE", nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountRole role;

  @JsonIgnore
  @Column(name = "PASSWORD", nullable = false, columnDefinition = "text")
  private String password;

  @Column(name = "BIRTH_DATE", nullable = false)
  private LocalDate birthDate;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private Set<AccountAddress> addresses;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private Set<Cart> carts;

  @PrePersist
  @PreUpdate
  public void buildRowIndex() {
    this.setDefaults();
    super.buildRowIndex();

    if (status != null) {
      this.appendToRowIndex(status.name());
    }
    this.appendToRowIndex(firstName);
    this.appendToRowIndex(lastName);
    this.appendToRowIndex(email);
    this.appendToRowIndex(phoneNumber);
    this.appendToRowIndex(username);
    this.appendToRowIndex(DateUtils.format(birthDate));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(this.getUserRole()));
  }

  @Override
  public String getUsername() {
    return username;
  }

  public String getUserRole() {
    return "ROLE_" + role.name();
  }
}
