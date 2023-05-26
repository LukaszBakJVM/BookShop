package com.example.book_rental.PenaltyAndMailSender;

import com.example.book_rental.Book.Book;
import com.example.book_rental.Book.BookRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailSender {
    private final JavaMailSender mailSender;
    private final BookRepository bookRepository;

    public MailSender(JavaMailSender mailSender, BookRepository bookRepository) {
        this.mailSender = mailSender;
        this.bookRepository = bookRepository;
    }
    public void sendMail(){
        List<Book> rented = bookRepository.findAllByLocalDateIsNotNull();
        SimpleMailMessage mail = new SimpleMailMessage();
        StringBuilder str=new StringBuilder();
        mail.setTo("bbzzyyczczeek@interia.pl");//recipient mail
        mail.setSubject("Ksiazki wypozyczone");
        for (Book m:rented
             ) {
            mail.setTo(m.getPerson().getEmail());//recipient mail
            
        }
        

    }
}
