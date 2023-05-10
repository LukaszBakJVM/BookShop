package com.example.book_rental.Person;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonsServices services;

    public PersonController(PersonsServices services) {
        this.services = services;
    }

    @PostMapping
    ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto) {
        PersonDto save = services.save(personDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }
    @GetMapping("/{id}")
    ResponseEntity<PersonDto>findById(@PathVariable long id){
        return ResponseEntity.ok(services.getPersonById(id));
    }
}