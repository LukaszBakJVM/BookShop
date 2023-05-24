package com.example.book_rental.RentBook;

import com.example.book_rental.Book.Book;
import com.example.book_rental.Book.BookRepository;
import com.example.book_rental.Exepction.NotAvailableException;
import com.example.book_rental.Exepction.PersonException;
import com.example.book_rental.Person.Person;
import com.example.book_rental.Person.PersonRepository;
import org.springframework.stereotype.Service;



import java.time.LocalDate;

import java.time.temporal.ChronoUnit;


@Service
public class RentBookServices {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final RentBookRepository rentBookRepository;
    private final RentBookMapper mapper;

    public RentBookServices(BookRepository bookRepository, PersonRepository personRepository,
                            RentBookRepository rentBookRepository, RentBookMapper mapper) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
        this.rentBookRepository = rentBookRepository;
        this.mapper = mapper;
    }
RentBookDto rentBook(RentBookDto rentBookDto) {

    LocalDate dayOfRent = LocalDate.now();
    LocalDate dayOfReturn = dayOfRent.plusDays(30);

    RentBook rentBook = new RentBook();
    Person person = personRepository.findById(rentBookDto.getPersonId()).orElseThrow(() ->
            new PersonException("Brak uzytkownika o id " + rentBookDto.getPersonId()));
    Book book = bookRepository.findByIdAndLocalDateIsNull(rentBookDto.getBookId())
            .orElseThrow(() -> {
                throw new NotAvailableException();
            });

    book.setLocalDate(dayOfRent);
    book.setReturnDate(dayOfReturn);
    book.setPerson(person);

    rentBook.setPerson(person);
    rentBook.setBook(book);
    RentBook save = rentBookRepository.save(rentBook);
    return mapper.map(save);
      }


    void endRent(String s) {
        LocalDate localDateReturn = LocalDate.now();

        RentBook rentBook = rentBookRepository.findByBook_Title(s).orElseThrow();

        Book book = rentBook.getBook();
        LocalDate returnDate = book.getReturnDate();
        if (!returnDate.isAfter(localDateReturn)) {
            long daysOutOfTime = ChronoUnit.DAYS.between(returnDate,localDateReturn);

            double penalty = book.getPricePerDayOver30() * daysOutOfTime;
            System.err.println("Przekroczyłeś dni wyporzyczenia o " + daysOutOfTime);
            System.out.print("Kwota oplaty karnej to  " + penalty);


        } else {
            book.setLocalDate(null);
            book.setReturnDate(null);
            book.setPerson(null);
            rentBookRepository.delete(rentBook);


        }
    }

}
