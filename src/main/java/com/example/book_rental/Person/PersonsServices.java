package com.example.book_rental.Person;


import com.example.book_rental.Book.Book;
import com.example.book_rental.Exepction.PersonException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonsServices {
    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonsServices(PersonRepository personRepository,PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }


    PersonDto getPersonById(long id){
        Person person = personRepository.findById(id).orElseThrow();
        return personMapper.map(person);
    }
    public void deletePerson(String email){
        Person person = personRepository.findByEmail(email).orElseThrow();
        List<Book> books = person.getBooks();
        if (!books.isEmpty()){
            throw new PersonException("Nie mozna usunac osoby ma wyporzyczone ksiazki  ");
        }else {
            personRepository.delete(person);
        }
    }

}
