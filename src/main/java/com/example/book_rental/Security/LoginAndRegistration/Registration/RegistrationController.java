package com.example.book_rental.Security.LoginAndRegistration.Registration;

import com.example.book_rental.Person.PersonDto;
import com.example.book_rental.Person.PersonsServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class RegistrationController {
   private final PersonsServices personServices;

    public RegistrationController(PersonsServices personServices) {
        this.personServices = personServices;
    }
    @PostMapping("/registration")
    ResponseEntity<PersonDto> savePerson(@Valid @RequestBody PersonDto personDto) {
        PersonDto save = personServices.save(personDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }
}


