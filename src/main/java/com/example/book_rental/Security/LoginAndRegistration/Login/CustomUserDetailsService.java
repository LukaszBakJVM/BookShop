package com.example.book_rental.Security.LoginAndRegistration.Login;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final LoginServices loginServices;

    public CustomUserDetailsService(LoginServices loginServices) {
        this.loginServices = loginServices;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginServices.findByEmail(username)
                .map(this::createUserDetails).orElseThrow(()->
                        new UsernameNotFoundException("Bledny email "+username));
    }


    private UserDetails createUserDetails(PersonLoginDto login) {
        return User.builder()
                .username(login.getEmail())
                .password(login.getPassword())
                .roles(login.getRole().toArray(String[]::new))
                .build();
    }


}
