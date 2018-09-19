package com.tvestergaard.rest;

import com.tvestergaard.rest.entities.Person;

import java.util.List;

public interface PersonFacade
{
    Person addPerson(Person p);

    Person deletePerson(int id);

    Person getPerson(int id);

    PersonDTO getPersonDTO(int id);

    List<Person> getAllPersons();

    List<PersonDTO> getAllPersonDTOs();

    Person editPerson(Person p);
}
