package net.highfi.bnpparibasfortis.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.highfi.bnpparibasfortis.bookstore.entities.Cart;

@Repository("cartRepository")
public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUuid(String uuid);
}
