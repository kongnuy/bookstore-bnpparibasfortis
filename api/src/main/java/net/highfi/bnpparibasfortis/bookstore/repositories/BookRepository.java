package net.highfi.bnpparibasfortis.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.highfi.bnpparibasfortis.bookstore.entities.Book;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, Long> {
}
