package net.highfi.bnpparibasfortis.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.highfi.bnpparibasfortis.bookstore.entities.CartItem;

@Repository("cartItemRepository")
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
