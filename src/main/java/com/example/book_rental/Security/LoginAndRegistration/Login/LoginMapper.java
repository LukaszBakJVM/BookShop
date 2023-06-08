package com.example.book_rental.Security.LoginAndRegistration.Login;

import com.example.book_rental.Person.Person;

import com.example.book_rental.Security.Role;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginMapper {
    PersonLoginDto map(Person person){
        String email = person.getEmail();
        String password = person.getPassword();
        Set<String> role = person.getRoles().stream().map(Role::getName)
                .collect(Collectors.toSet());
        return new PersonLoginDto(email,password,role);
    }
}
