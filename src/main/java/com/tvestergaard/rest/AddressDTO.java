package com.tvestergaard.rest;

import com.tvestergaard.rest.entities.Address;

public class AddressDTO
{

    public int    id;
    public String country;
    public String city;
    public String street;
    public String number;

    public AddressDTO(Address from)
    {
        this.id = from.getId();
        this.country = from.getCountry();
        this.city = from.getCity();
        this.street = from.getStreet();
        this.number = from.getNumber();
    }
}
