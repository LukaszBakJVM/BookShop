package com.example.book_rental.Book;

import com.example.book_rental.Exepction.BookException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;


class BookServicesTest {
    LocalDate localDate =LocalDate.now();
  private final   BookDto bookDto = new BookDto();
  private final   BookDto bookDto1=new BookDto();
   private final Book book = new Book("author1","tytul1","isbn1");
  private final   Book book1 = new Book("author2","titul2","isbn2");
    @InjectMocks
  private   BookServices bookServices;
    @Mock
  private   BookRepository bookRepository;
    @Mock
    private  BookMapper bookMapper;


    @BeforeEach
    public void init() {
        openMocks(this);

    }

    @Test
    public void testSave() {
       //when
        when(bookMapper.map(bookDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.map(book)).thenReturn(bookDto);


        BookDto result = bookServices.save(bookDto);



        //then
        assertNotNull(result);

    }




    @Test
  public   void allAvailableBooks() {
        book.setLocalDate(null);
        book1.setLocalDate(localDate);
         when(bookMapper.map(book)).thenReturn(bookDto);
         when(bookMapper.map(book1)).thenReturn(bookDto1);
        when(bookRepository.findAllByLocalDateIsNull()).thenReturn(List.of(book));



        List<BookDto> bookDtos = bookServices.allAvailableBooks();
        assertNotNull(bookDtos);
        assertEquals(1,bookDtos.size());

    }

    @Test
    void allRentBooks() {
        book.setLocalDate(localDate);
        book1.setLocalDate(localDate);

        when(bookRepository.findAllByLocalDateIsNull()).thenReturn(List.of(book1,book));



        List<BookDto> bookDtos = bookServices.allAvailableBooks();
        assertNotNull(bookDtos);
        assertEquals(2,bookDtos.size());
    }

    @Test
    void deleteBookDateNull() {
        long l = 1L;
        book.setId(l);
        book.setLocalDate(null);
        when(bookRepository.findById(l)).thenReturn(Optional.of(book));
        when(bookRepository.findAllByLocalDateIsNull()).thenReturn(List.of(book));
        assertDoesNotThrow(()->bookServices.deleteBook(l));
    }
    @Test()
    void deleteBookDateNotNull() {
        long l= 1L;
        book.setId(l);
        book.setLocalDate(localDate);
        when(bookRepository.findById(l)).thenReturn(Optional.of(book));
        when(bookRepository.findAllByLocalDateIsNotNull()).thenReturn(List.of(book));
        assertThrows(BookException.class,()->bookServices.deleteBook(l));
    }



}