package com.example.book_rental.PenaltyAndMailSender;

import com.example.book_rental.Book.Book;
import com.example.book_rental.Book.BookRepository;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SendMail {
    private final JavaMailSender mailSender;
    private final BookRepository bookRepository;


    public SendMail(JavaMailSender mailSender, BookRepository bookRepository) {
        this.mailSender = mailSender;
        this.bookRepository = bookRepository;

    }
    public void sendMail() {
        Set<String>mailCheck=new HashSet<>();
        List<Book> rented = bookRepository.findAllByLocalDateIsNotNull();
        SimpleMailMessage mail = new SimpleMailMessage();
        StringBuilder str = new StringBuilder();
        mail.setSubject("Ksiazki wypozyczone");
        for (Book m : rented
        ) {
            if (!mailCheck.contains(m.getPerson().getEmail())) {
                mailCheck.add(m.getPerson().getEmail());


                mail.setTo(m.getPerson().getEmail());//recipient mail
                long id = m.getPerson().getId();
                List<Book> allByPersonId = bookRepository.findAllByPersonId(id);
                for (Book s : allByPersonId
                ) {
                    str.append(s.getTitle());
                }
                mail.setText(str.toString());

                mailSender.send(mail);
            }
        }
    }
}


