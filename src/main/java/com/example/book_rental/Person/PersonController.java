package com.example.book_rental.Person;


import com.example.book_rental.Book.BookDto;
import com.example.book_rental.Book.BookServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonsServices services;
    private final BookServices bookServices;

    public PersonController(PersonsServices services, BookServices bookServices) {
        this.services = services;
        this.bookServices = bookServices;
    }


    @GetMapping("/{id}")
    ResponseEntity<PersonDto>findById(@PathVariable long id){
        return ResponseEntity.ok(services.getPersonById(id));
    }
    @GetMapping("/borrowed/{id}")
    ResponseEntity<List<BookDto>>borrowed(@PathVariable long id){
        return ResponseEntity.ok(bookServices.findAllBooksByPersonId(id));
    }
}