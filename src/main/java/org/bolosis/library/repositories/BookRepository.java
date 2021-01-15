package org.bolosis.library.repositories;

import org.bolosis.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCopiesGreaterThan(int copies);

    @Transactional
    @Modifying
    @Query(value = "update books set copies = :copies where id = :id",
            nativeQuery = true)
    void orderBook(@Param("id") Long id, @Param("copies") int copies);
}
