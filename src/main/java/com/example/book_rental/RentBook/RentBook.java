package com.example.book_rental.RentBook;

import com.example.book_rental.Book.Book;
import com.example.book_rental.Person.Person;
import jakarta.persistence.*;



@Entity
public class RentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Person person;
    @OneToOne
    private  Book book;

    public RentBook() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
