package com.example.book_rental.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Override
    List<Book> findAll();
    List<Book>findAllByLocalDateIsNull();
    List<Book>findAllByLocalDateIsNotNull();
    Optional<Book>findByIdAndLocalDateIsNotNull(long id);
    Optional<Book>findByIdAndLocalDateIsNull(long id);

}
