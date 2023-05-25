package com.example.book_rental.Person;

import com.example.book_rental.Book.Book;

import com.example.book_rental.Person.Address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.pl.PESEL;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 2)
    private String firstName;
    @NotNull
    @Size(min = 2)
    private String lastName;
    @NotNull
   // @PESEL
    private String pesel;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    private double penalty;
    @OneToOne
    private Address address;
    @OneToMany(mappedBy = "person")
    private List<Book>books =new ArrayList<>();

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(pesel, person.pesel) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, pesel, email);
    }
}
