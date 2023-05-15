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
RentBookDto rentBook(RentBookDto rentBookDto){
    LocalDate dayOfRent =LocalDate.now();
    LocalDate dayOfReturn=dayOfRent.plusDays(30);

        RentBook rentBook =new RentBook();
    Person person = personRepository.findById(rentBookDto.getPersonId()).orElseThrow(() ->
            new PersonException("Brak uzytkownika o id " + rentBookDto.getPersonId()));
    Book book = bookRepository.findByIdAndLocalDateIsNull(rentBookDto.getBookId())
            .orElseThrow(() -> {
                throw new NotAvailableException();
            });
    book.setLocalDate(dayOfRent);
    book.setReturnDate(dayOfReturn);

    rentBook.setPerson(person);
    rentBook.setBook(book);
    RentBook save = rentBookRepository.save(rentBook);
    return mapper.map(save);



}
}
