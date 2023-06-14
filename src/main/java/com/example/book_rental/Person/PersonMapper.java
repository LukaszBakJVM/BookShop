package com.example.book_rental.Person;

import com.example.book_rental.Person.Address.Address;
import com.example.book_rental.Security.LoginAndRegistration.Registration.Role.Role;
import com.example.book_rental.Security.LoginAndRegistration.Registration.Role.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    private final String USER = "User";
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public PersonMapper(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Person map(PersonDto dto) {
        Person person = new Person();
        Address address = new Address();
        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setPesel(dto.getPesel());
        person.setEmail(dto.getEmail());
        String password = passwordEncoder.encode(dto.getPassword());
        person.setPassword(password);
        Role role = roleRepository.findByName(USER).orElseThrow();
        person.getRoles().add(role);
        address.setId(dto.getAddressId());
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        person.setAddress(address);
        return person;

    }

    public PersonDto map(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setPesel(person.getPesel());
        dto.setEmail(person.getEmail());
        dto.setAddressId(person.getAddress().getId());
        dto.setCity(person.getAddress().getCity());
        dto.setStreet(person.getAddress().getStreet());
        dto.setHouseNumber(person.getAddress().getHouseNumber());
        return dto;
    }


}
