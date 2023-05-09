package com.example.book_rental.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Override
    List<Book> findAll();
    List<Book>findAllByLocalDateIsNull();
    List<Book>findAllByLocalDateIsNotNull();
}
