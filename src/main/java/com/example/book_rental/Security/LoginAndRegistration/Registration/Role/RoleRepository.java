package com.example.book_rental.Security.LoginAndRegistration.Registration.Role;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Optional<Role>findByName(String name);
}
