package com.tvestergaard.rest;

import com.tvestergaard.rest.entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDTO
{
    public int              id;
    public String           firstName;
    public String           lastName;
    public String           phone;
    public List<AddressDTO> addresses = new ArrayList<>();

    public PersonDTO(Person from)
    {
        this.id = from.getId();
        this.firstName = from.getFirstName();
        this.lastName = from.getLastName();
        this.phone = from.getPhone();
        this.addresses = from.getAddresses().stream().map(AddressDTO::new).collect(Collectors.toList());
    }
}
