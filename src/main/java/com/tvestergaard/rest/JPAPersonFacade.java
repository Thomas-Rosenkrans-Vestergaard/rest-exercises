package com.tvestergaard.rest;

import com.tvestergaard.rest.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPAPersonFacade implements PersonFacade
{

    private final EntityManagerFactory entityManagerFactory;

    public JPAPersonFacade(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override public Person addPerson(Person p)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            return p;
        } finally {
            entityManager.close();
        }
    }

    @Override public Person deletePerson(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Person person = entityManager.find(Person.class, id);
            if (person == null)
                return null;
            entityManager.getTransaction().begin();
            entityManager.remove(person);
            entityManager.getTransaction().commit();
            return person;
        } finally {
            entityManager.close();
        }
    }

    @Override public Person getPerson(int id)
    {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(Person.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override public List<Person> getAllPersons()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Person.findAll", Person.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override public Person editPerson(Person p)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            int updated = entityManager
                    .createQuery("UPDATE Person p SET p.firstName = ?1, p.lastName = ?2, p.phone = ?3 WHERE p.id = ?4")
                    .setParameter(1, p.getFirstName())
                    .setParameter(2, p.getLastName())
                    .setParameter(3, p.getPhone())
                    .setParameter(4, p.getId())
                    .executeUpdate();

            entityManager.getTransaction().commit();

            if (updated < 1)
                return null;

            return entityManager.find(Person.class, p.getId());

        } finally {
            entityManager.close();
        }
    }
}
