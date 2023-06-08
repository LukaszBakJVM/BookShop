package com.example.book_rental.Security.LoginAndRegistration.Login;

import com.example.book_rental.Person.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServices {

    private final PersonRepository personRepository;
    private final LoginMapper loginMapper;

    public LoginServices(PersonRepository personRepository, LoginMapper loginMapper) {
        this.personRepository = personRepository;
        this.loginMapper = loginMapper;
    }

    Optional<PersonLoginDto>findByEmail(String email){
      return   personRepository.findByEmail(email)
              .map(loginMapper::map);


    }
}
