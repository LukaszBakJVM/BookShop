package com.example.book_rental.Person.Address;

import com.example.book_rental.Person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 2)
    private String city;
    @NotNull
    @Size(min = 2)
    private String street;
    @NotNull

    private String houseNumber;
  //  @OneToOne
    //@JoinColumn(name = "idklienta",in)


  //  private Person person;

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

  /*  public Person getPerson() {
        return person;
    }

   public void setPerson(Person person) {
        this.person = person;
    }*/
}
