package com.example.book_rental.RentBook;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RentBookRepository extends JpaRepository<RentBook,Long> {
    Optional<RentBook>findByBook_Title(String title);
    Optional<RentBook>findByPersonPesel(String pesel);



}
