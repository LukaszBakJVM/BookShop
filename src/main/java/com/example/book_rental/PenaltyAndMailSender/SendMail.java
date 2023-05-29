package com.example.book_rental.PenaltyAndMailSender;

import com.example.book_rental.Book.Book;
import com.example.book_rental.Book.BookRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SendMail {
    private final JavaMailSender mailSender;
    private final BookRepository bookRepository;


    public SendMail(JavaMailSender mailSender, BookRepository bookRepository) {
        this.mailSender = mailSender;
        this.bookRepository = bookRepository;

    }

    public void sendMail() {
        String checkMail = "";

        List<Book> rented = bookRepository.findAllByLocalDateIsNotNull();
        SimpleMailMessage mail = new SimpleMailMessage();
        StringBuilder str = new StringBuilder();
        mail.setSubject("Ksiazki wypozyczone");
        for (Book m : rented) {

            if (!checkMail.equals(m.getPerson().getEmail())) {
                checkMail = m.getPerson().getEmail();


                mail.setTo(m.getPerson().getEmail());//recipient mail
                long id = m.getPerson().getId();
                List<Book> allByPersonId = bookRepository.findAllByPersonId(id);
                for (Book s : allByPersonId
                ) {
                    str.append(s);
                    str.append(System.lineSeparator());
                }
                double penalty = m.getPerson().getPenalty();
                if (penalty != 0) {
                    str.append(penalty);
                }
                mail.setText(str.toString());
                mailSender.send(mail);
                str.setLength(0);
            }
        }
    }

}

