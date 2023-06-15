package com.example.book_rental.Security;

import com.example.book_rental.Book.BookDto;
import com.example.book_rental.Book.BookServices;
import com.example.book_rental.Person.PersonsServices;
import com.example.book_rental.Security.LoginAndRegistration.Registration.Role.RoleServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final BookServices bookServices;
    private final PersonsServices personsServices;
    private final RoleServices roleServices;

    public AdminController(BookServices bookServices, PersonsServices personsServices, RoleServices roleServices) {
        this.bookServices = bookServices;
        this.personsServices = personsServices;
        this.roleServices = roleServices;
    }


    @PostMapping
    ResponseEntity<BookDto> saveBook(@Valid @RequestBody BookDto bookDto) {
        BookDto save = bookServices.save(bookDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBookById(@PathVariable long id) {
        bookServices.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/rented")
    ResponseEntity<List<BookDto>>rented() {
        return ResponseEntity.ok(bookServices.allRentBooks());
    }
    @DeleteMapping("/{email}")
    ResponseEntity<?>deletePersonByEmail(@PathVariable String email){
        personsServices.deletePerson(email);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{email}")
    ResponseEntity<?>addAdminRight(@PathVariable String email){
        return roleServices.forAdmin(email)
                .map(a->ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}