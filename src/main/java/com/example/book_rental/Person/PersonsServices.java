package com.example.book_rental.Person;

import com.example.book_rental.Person.Address.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonsServices {
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final PersonMapper personMapper;

    public PersonsServices(PersonRepository personRepository, AddressRepository addressRepository,
                           PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.personMapper = personMapper;
    }
    PersonDto save(PersonDto dto){
        Person map = personMapper.map(dto);
        addressRepository.save(map.getAddress());
        Person save = personRepository.save(map);
        return personMapper.map(save);
    }
    PersonDto getPersonById(long id){
        Person person = personRepository.findById(id).orElseThrow();
        return personMapper.map(person);
    }

}
