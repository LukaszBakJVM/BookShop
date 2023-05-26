package com.example.book_rental.RentBook;

import com.example.book_rental.Book.Book;
import com.example.book_rental.Book.BookRepository;
import com.example.book_rental.Exepction.NotAvailableException;
import com.example.book_rental.Exepction.PersonException;
import com.example.book_rental.Person.Person;
import com.example.book_rental.Person.PersonRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;




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


        RentBook rentBook = rentBookRepository.findByBook_Title(s).orElseThrow();

        Book book = rentBook.getBook();
        double penalty = book.getPerson().getPenalty();
        if (penalty!=0){
            System.err.print("Kara za opoznienie "+penalty);

          //  TODO    Redirected to payment


        } else {
            book.setLocalDate(null);
            book.setReturnDate(null);
            book.setPerson(null);
            rentBookRepository.delete(rentBook);


        }
    }

}
