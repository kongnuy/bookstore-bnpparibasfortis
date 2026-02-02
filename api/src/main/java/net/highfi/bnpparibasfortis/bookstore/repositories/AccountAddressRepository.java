package net.highfi.bnpparibasfortis.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.highfi.bnpparibasfortis.bookstore.entities.AccountAddress;

@Repository("accountAddressRepository")
public interface AccountAddressRepository extends JpaRepository<AccountAddress, Long> {
}
