package com.example.book_rental.Book;



public class BookDto {
    private long id;
    private String author;
    private String title;
    private String isbn;
    private final double pricePerDayOver30 =5;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPricePerDayOver30() {
        return pricePerDayOver30;
    }
}
