package com.example.book_rental.Book;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }
    //Admin
    @PostMapping
    ResponseEntity<BookDto>saveBook(@Valid @RequestBody BookDto bookDto){
        BookDto save = bookServices.save(bookDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }
    //Admin
    @DeleteMapping("/{id}")
    ResponseEntity<?>deleteById(@PathVariable long id) {
        bookServices.deleteBook(id);
      return   ResponseEntity.noContent().build();
    }
    @GetMapping("/available")
    ResponseEntity<List<BookDto>>vailable(){
        return ResponseEntity.ok(bookServices.allAvailableBooks());

    }
    @GetMapping("/rented")
    ResponseEntity<List<BookDto>>rented(){
        return ResponseEntity.ok(bookServices.allRentBooks());
    }
}
