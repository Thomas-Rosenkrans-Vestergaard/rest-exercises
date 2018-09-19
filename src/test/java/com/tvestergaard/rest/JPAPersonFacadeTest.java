package com.tvestergaard.rest;

import com.tvestergaard.rest.JPAPersonFacade;
import com.tvestergaard.rest.entities.Person;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static junit.framework.TestCase.*;

public class JPAPersonFacadeTest
{
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("rest-api-test-pu");
    private              JPAPersonFacade      instance;

    @Before
    public void setUp() throws Exception
    {
        instance = new JPAPersonFacade(emf);
    }

    @Test
    public void addPerson()
    {
        Person person = instance.addPerson(new Person("FirstName", "LastName", "78945612"));
        assertEquals(person, instance.getPerson(person.getId()));
    }

    @Test
    public void deletePerson()
    {
        Person person = instance.addPerson(new Person("FirstName", "LastName", "78945612"));
        assertEquals(person, instance.getPerson(person.getId()));
        instance.deletePerson(person.getId());
        assertNull(instance.getPerson(person.getId()));
    }

    @Test
    public void getPerson()
    {
        Person thomas = instance.getPerson(1);

        assertEquals(1, thomas.getId());
        assertEquals("Thomas", thomas.getFirstName());
        assertEquals("Vestergaard", thomas.getLastName());
        assertEquals("26508830", thomas.getPhone());
    }

    @Test
    public void getAllPersons()
    {
        List<Person> persons = instance.getAllPersons();

        assertTrue(persons.size() >= 4);
        assertEquals("Thomas", persons.get(0).getFirstName());
        assertEquals("Sanne", persons.get(3).getFirstName());
    }

    @Test
    public void editPerson()
    {
        Person person = instance.addPerson(new Person("a", "b", "c"));

        assertEquals("a", instance.getPerson(person.getId()).getFirstName());
        person.setFirstName("d");
        person = instance.editPerson(person);
        assertEquals("d", person.getFirstName());
    }
}