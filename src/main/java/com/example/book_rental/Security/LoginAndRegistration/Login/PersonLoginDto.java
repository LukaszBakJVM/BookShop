package com.example.book_rental.Security.LoginAndRegistration.Login;

import java.util.Set;

public class PersonLoginDto {
    private final  String email;
    private final String password;
    private final Set<String>role;



    public PersonLoginDto(String email, String password, Set<String> role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRole() {
        return role;
    }
}
