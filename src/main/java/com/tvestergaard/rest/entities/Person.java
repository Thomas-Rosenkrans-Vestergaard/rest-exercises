package com.tvestergaard.rest.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p ORDER BY p.id ASC"),
        @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
})
public class Person
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;

    @Column(nullable = false, length = 8)
    private String phone;

    public Person()
    {

    }

    public Person(String firstName, String lastName, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getId()
    {
        return this.id;
    }

    public Person setId(int id)
    {
        this.id = id;
        return this;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public Person setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public Person setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public Person setPhone(String phone)
    {
        this.phone = phone;
        return this;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
               Objects.equals(firstName, person.firstName) &&
               Objects.equals(lastName, person.lastName) &&
               Objects.equals(phone, person.phone);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, phone);
    }

    @Override public String toString()
    {
        return "Person{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", phone='" + phone + '\'' +
               '}';
    }
}
