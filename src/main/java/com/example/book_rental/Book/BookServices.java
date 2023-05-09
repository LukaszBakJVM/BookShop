package com.example.book_rental.Book;

import com.example.book_rental.Exepction.BokException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {
    private final BookRepository repository;
    private final BookMapper mapper;

    public BookServices(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    BookDto save(BookDto bookDto) {
        Book map = mapper.map(bookDto);
        Book save = repository.save(map);
        return mapper.map(save);
    }

        List<BookDto>allAvailableBooks(){
        return     repository.findAllByLocalDateIsNull()
                .stream().map(mapper::map).toList();
          }
          List<BookDto>allRentBooks(){
        return repository.findAllByLocalDateIsNotNull()
                .stream().map(mapper::map).toList();
          }



    public void deleteBook(long id) {
        Book book = repository.findById(id).orElseThrow();
        List<Book> allByLocalDateIsNull = repository.findAllByLocalDateIsNull();
        if (!allByLocalDateIsNull.contains(book)) {
           throw new BokException();

        } else {
            repository.deleteById(id);
        }
    }
}