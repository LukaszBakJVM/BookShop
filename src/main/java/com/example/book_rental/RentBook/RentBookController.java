package com.example.book_rental.RentBook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rentBook")
public class RentBookController {
    private final RentBookServices services;

    public RentBookController(RentBookServices services) {
        this.services = services;
    }
    @PostMapping
    ResponseEntity<RentBookDto>rent(@RequestBody RentBookDto rentBookDto){
        RentBookDto rentedOut = services.rentBook(rentBookDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(rentedOut.getId())
                .toUri();
        return ResponseEntity.created(uri).body(rentedOut);

    }
}
