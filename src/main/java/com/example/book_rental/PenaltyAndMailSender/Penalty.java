package com.example.book_rental.PenaltyAndMailSender;

import com.example.book_rental.Book.Book;
import com.example.book_rental.Book.BookRepository;
import com.example.book_rental.Person.Person;
import com.example.book_rental.Person.PersonRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class Penalty {
    private final BookRepository repository;
  private final   PersonRepository personRepository;

    public Penalty(BookRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

public void findBookOver30days(){
        double penalty=0;
    LocalDate localDateOfReturn = LocalDate.now();
    List<Book> all = repository.findAllByLocalDateIsNotNull();
    for (Book p:all
         ) {
        LocalDate returnDate = p.getReturnDate();
        if (!returnDate.isAfter(localDateOfReturn)) {
            long daysOutOfTime = ChronoUnit.DAYS.between(returnDate, localDateOfReturn);

            penalty += p.getPricePerDayOver30() * daysOutOfTime;
        }

            Person person = p.getPerson();
            person.setPenalty(penalty);
            personRepository.save(person);
            System.out.println("Kwota oplaty karnej to  " + penalty);
        }



}
}
