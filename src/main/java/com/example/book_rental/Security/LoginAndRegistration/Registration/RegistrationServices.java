package com.example.book_rental.Security.LoginAndRegistration.Registration;

import com.example.book_rental.Exepction.PersonException;
import com.example.book_rental.Person.Address.AddressRepository;
import com.example.book_rental.Person.Person;
import com.example.book_rental.Person.PersonDto;
import com.example.book_rental.Person.PersonMapper;
import com.example.book_rental.Person.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServices {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final AddressRepository addressRepository;



    public RegistrationServices(PersonRepository repository, PersonMapper personMapper,
                                AddressRepository addressRepository) {
        this.personRepository = repository;
        this.personMapper = personMapper;
        this.addressRepository = addressRepository;

    }

    public PersonDto save(PersonDto dto) {
        Optional<Person> byEmail = personRepository.findByEmail(dto.getEmail());
        byEmail.ifPresent(p -> {
            throw new PersonException("Taki email jest juz zapisany w bazie "
                    + dto.getEmail());
        });
        Person map = personMapper.map(dto);
        addressRepository.save(map.getAddress());
        Person save = personRepository.save(map);
        return personMapper.map(save);
    }
}
