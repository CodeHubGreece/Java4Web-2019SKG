package org.regeneration.rest.restless.repository;

import org.regeneration.rest.restless.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByIsbn(String isbn);

    @Query(value = "SELECT * FROM Book b WHERE SOUNDEX(b.title) LIKE SOUNDEX(:title)",
            nativeQuery = true)
    List<Book> findMatchingTitle(@Param("title") String title);
}
