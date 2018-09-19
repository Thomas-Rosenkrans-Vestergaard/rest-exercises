package com.tvestergaard.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tvestergaard.rest.entities.Person;

import java.util.List;

public class JsonConverter
{

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Person getPersonFromJson(String js)
    {
        return gson.fromJson(js, Person.class);
    }

    public String getJSONFromPerson(Person p)
    {
        return gson.toJson(p);
    }

    public String getJSONFromPersons(List<Person> persons)
    {
        return gson.toJson(persons);
    }
}
