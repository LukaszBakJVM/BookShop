package com.example.book_rental.Person;


import com.example.book_rental.Person.Address.AddressRepository;
import org.springframework.stereotype.Service;




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


    PersonDto getPersonById(long id){
        Person person = personRepository.findById(id).orElseThrow();
        return personMapper.map(person);
    }

}
