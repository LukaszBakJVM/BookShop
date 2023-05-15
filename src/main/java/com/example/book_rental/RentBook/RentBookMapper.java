package com.example.book_rental.RentBook;
import com.example.book_rental.Book.Book;
import com.example.book_rental.Book.BookRepository;
import com.example.book_rental.Person.Person;
import com.example.book_rental.Person.PersonRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class RentBookMapper {
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;

    public RentBookMapper(PersonRepository personRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

 RentBookDto map(RentBook rentBook){

        RentBookDto dto=new RentBookDto();
    dto.setId(rentBook.getId());
    Person person =rentBook.getPerson();
    dto.setPersonId(person.getId());
    Book book =rentBook.getBook();
    dto.setBookId(book.getId());

    return dto;
}

}
