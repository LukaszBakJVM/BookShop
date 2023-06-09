package com.example.book_rental.Security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        PathRequest.H2ConsoleRequestMatcher h2Console = PathRequest.toH2Console();
        http.formLogin().permitAll();
        http.authorizeHttpRequests().anyRequest().permitAll();

       // http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll());
        http.csrf(c->c.ignoringRequestMatchers(h2Console));
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
