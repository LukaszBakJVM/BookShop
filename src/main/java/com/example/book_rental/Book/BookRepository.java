package com.example.book_rental.Book;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Override
    List<Book> findAll();
    List<Book>findAllByLocalDateIsNull();
    List<Book>findAllByLocalDateIsNotNull();
    List<Book>findByPersonId(long id);
    Optional<Book>findByIdAndLocalDateIsNull(long id);
    List<Book>findAllByPersonId(long id);

}
