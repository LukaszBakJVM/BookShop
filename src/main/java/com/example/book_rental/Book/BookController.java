package com.example.book_rental.Book;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping("/available")
    ResponseEntity<List<BookDto>>vailable(){
        return ResponseEntity.ok(bookServices.allAvailableBooks());

    }


}
