package com.tvestergaard.rest;

import com.tvestergaard.rest.entities.Person;

import java.util.List;

public interface PersonFacade
{
    Person addPerson(Person p);

    Person deletePerson(int id);

    Person getPerson(int id);

    List<Person> getAllPersons();

    Person editPerson(Person p);
}
