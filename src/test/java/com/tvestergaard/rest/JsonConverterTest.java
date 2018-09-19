package com.tvestergaard.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tvestergaard.rest.entities.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class JsonConverterTest
{

    private final Gson          gson = new GsonBuilder().create();
    private       JsonConverter instance;

    @Before
    public void setUp() throws Exception
    {
        instance = new JsonConverter();
    }

    @Test
    public void getPersonFromJson()
    {
        Person person = new Person();
        person.setId(1);
        person.setFirstName("First");
        person.setLastName("Last");
        person.setPhone("Phone");

        String json  = gson.toJson(person);
        Person after = instance.getPersonFromJson(json);
        assertEquals(person, after);
    }

    @Test
    public void getJSONFromPerson()
    {
        Person person = new Person();
        person.setId(1);
        person.setFirstName("First");
        person.setLastName("Last");
        person.setPhone("Phone");

        String json  = instance.getJSONFromPerson(person);
        Person after = gson.fromJson(json, Person.class);
        assertEquals(person, after);
    }

    @Test
    public void getJSONFromPersons()
    {
        List<Person> persons = new ArrayList();
        persons.add(new Person("a", "b", "c"));
        persons.add(new Person("d", "e", "f"));

        String       json  = instance.getJSONFromPersons(persons);
        List<Person> after = gson.fromJson(json, new TypeToken<List<Person>>() {}.getType());
        assertEquals(persons, after);
    }
}