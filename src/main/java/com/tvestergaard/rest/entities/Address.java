package com.tvestergaard.rest.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Address
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    private String country;
    private String city;
    private String street;
    private String number;

    @ManyToMany
    private Collection<Person> persons = new ArrayList<>();

    public Address()
    {

    }

    public Address(String country, String city, String street, String number)
    {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public int getId()
    {
        return this.id;
    }

    public Address setId(int id)
    {
        this.id = id;
        return this;
    }

    public String getCountry()
    {
        return this.country;
    }

    public Address setCountry(String country)
    {
        this.country = country;
        return this;
    }

    public String getCity()
    {
        return this.city;
    }

    public Address setCity(String city)
    {
        this.city = city;
        return this;
    }

    public String getStreet()
    {
        return this.street;
    }

    public Address setStreet(String street)
    {
        this.street = street;
        return this;
    }

    public String getNumber()
    {
        return this.number;
    }

    public Address setNumber(String number)
    {
        this.number = number;
        return this;
    }

    public Collection<Person> getPersons()
    {
        return this.persons;
    }

    public Address setPersons(Collection<Person> persons)
    {
        this.persons = persons;
        return this;
    }

    public void addPerson(Person person)
    {
        this.persons.add(person);
    }
}
