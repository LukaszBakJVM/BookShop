package com.example.book_rental.Person;


import com.example.book_rental.Book.BookDto;
import com.example.book_rental.Book.BookServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    ResponseEntity<PersonDto> savePerson(@Valid @RequestBody PersonDto personDto) {
        PersonDto save = services.save(personDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
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