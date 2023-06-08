package com.example.book_rental.Person;

import com.example.book_rental.Person.Address.Address;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    Person map(PersonDto dto){
        Person person=new Person();
        Address address =new Address();
        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setPesel(dto.getPesel());
        person.setEmail(dto.getEmail());
        person.setPassword(dto.getPassword());
        address.setId(dto.getAddressId());
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        person.setAddress(address);
        return person;

    }
    PersonDto map(Person person){
        PersonDto dto =new PersonDto();
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
