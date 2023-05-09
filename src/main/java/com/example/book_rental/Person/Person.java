package com.example.book_rental.Person;

import com.example.book_rental.Person.Address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.lang.NonNull;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @PESEL
    private String pesel;
    @Email
    private String email;
    @OneToOne
    private Address address;
}
