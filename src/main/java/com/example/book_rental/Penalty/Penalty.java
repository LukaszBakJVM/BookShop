package com.example.book_rental.Penalty;

import com.example.book_rental.Book.Book;
import com.example.book_rental.Book.BookRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class Penalty {
    private final BookRepository repository;

    public Penalty(BookRepository repository) {
        this.repository = repository;
    }

public void findBookOver30days(){
    LocalDate localDateOfReturn = LocalDate.now();
    List<Book> all = repository.findAll();
    for (Book p:all
         ) {
        LocalDate returnDate = p.getReturnDate();
        if (!returnDate.isAfter(localDateOfReturn)) {
            long daysOutOfTime = ChronoUnit.DAYS.between(returnDate,localDateOfReturn);

            double penalty = p.getPricePerDayOver30() * daysOutOfTime;
            p.getPerson().setPenalty(penalty);
            System.err.println("Przekroczyłeś dni wyporzyczenia o " + daysOutOfTime);
            System.out.print("Kwota oplaty karnej to  " + penalty);
        }


    }
}
}
