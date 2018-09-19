package com.tvestergaard.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tvestergaard.rest.entities.Person;

import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("person")
public class PersonResource
{

    private static final PersonFacade persistence = new JPAPersonFacade(Persistence.createEntityManagerFactory("rest-api-pu"));
    private final        Gson         gson        = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("application/json;charset=utf-8")
    public Response get()
    {
        return Response.ok()
                       .entity(gson.toJson(persistence.getAllPersonDTOs()))
                       .build();
    }

    @GET
    @Path("{id: [0-9]+}")
    @Produces("application/json;charset=utf-8")
    public Response getById(@PathParam("id") int id)
    {
        PersonDTO person = persistence.getPersonDTO(id);

        if (person == null)
            return Response.status(NOT_FOUND).build();

        return Response.ok()
                       .entity(gson.toJson(person))
                       .build();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public Response post(String json)
    {
        Person person = gson.fromJson(json, Person.class);
        person = persistence.addPerson(person);
        return Response.status(CREATED)
                       .entity(gson.toJson(new PersonDTO(person)))
                       .build();
    }

    @PUT
    @Path("{id: [0-9]+}")
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public Response put(@PathParam("id") int id, String json)
    {
        Person person = gson.fromJson(json, Person.class);
        if (person == null)
            return Response.status(422).build();

        person.setId(id);
        person = persistence.editPerson(person);

        if (person == null)
            return Response.status(NOT_FOUND).build();

        return Response.ok(gson.toJson(new PersonDTO(person))).build();
    }

    @DELETE
    @Path("{id: [0-9]+}")
    @Produces("application/json;charset=utf-8")
    public Response delete(@PathParam("id") int id)
    {
        Person person = persistence.deletePerson(id);

        if (person == null)
            return Response.status(NOT_FOUND).build();

        return Response.status(204).build();
    }
}
