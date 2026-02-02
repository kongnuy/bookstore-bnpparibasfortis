package net.highfi.bnpparibasfortis.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.highfi.bnpparibasfortis.bookstore.entities.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByUuidOrUserNameOrEmailOrPhoneNumber(String uuid, String userName, String email,
      String phoneNumber);
}
