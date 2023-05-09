package com.example.book_rental.Book;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    Book map(BookDto dto){
        Book book =new Book();
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        return book;
    }
    BookDto map(Book book){
        BookDto dto =new BookDto();
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.getPricePerDayOver30();
        return dto;
    }
}
