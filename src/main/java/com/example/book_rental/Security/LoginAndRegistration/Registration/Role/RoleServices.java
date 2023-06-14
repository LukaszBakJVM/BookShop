package com.example.book_rental.Security.LoginAndRegistration.Registration.Role;

import com.example.book_rental.Person.Person;

import com.example.book_rental.Person.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServices {
    private final String ADMIN = "Admin";

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public RoleServices(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }
  public   Optional<Person> forAdmin(String email){
        Person person = personRepository.findByEmail(email).orElseThrow();
        Role role = roleRepository.findByName(ADMIN).orElseThrow();
         person.getRoles().add(role);
         personRepository.save(person);
         return Optional.of(person);


    }


}
