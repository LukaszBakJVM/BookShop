package com.example.book_rental.Exepction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Nie można usunąc ksiazki wyporzyczonej")

public class BookException extends RuntimeException{
}
