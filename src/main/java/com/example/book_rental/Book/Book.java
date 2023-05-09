package com.example.book_rental.Book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;



import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull

    @Size(min = 2)

    private String author;
    @NotNull
    @Size(min = 2)
    private String title;


    @NotNull
  //  @ISBN
    private String isbn;
    @Column(name = "data_wypozyczenia")
    private LocalDate localDate;
    private LocalDate returnDate;
    private final double pricePerDayOver30 =5;





    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull String author) {
        this.author = author;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    @NotNull
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotNull String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getPricePerDayOver30() {
        return pricePerDayOver30;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && author.equals(book.author) && title.equals(book.title) && isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, isbn);
    }
}
