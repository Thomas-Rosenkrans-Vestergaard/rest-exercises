package com.tvestergaard.rest;

import com.tvestergaard.rest.entities.Person;

import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("person")
public class PersonResource
{

    private static final PersonFacade  persistence   = new JPAPersonFacade(Persistence.createEntityManagerFactory("rest-api-pu"));
    private final        JsonConverter jsonConverter = new JsonConverter();

    @GET
    @Produces("application/json;charset=utf-8")
    public Response get()
    {
        return Response.ok()
                       .entity(jsonConverter.getJSONFromPersons(persistence.getAllPersons()))
                       .build();
    }

    @GET
    @Path("{id: [0-9]+}")
    @Produces("application/json;charset=utf-8")
    public Response getById(@PathParam("id") int id)
    {
        Person person = persistence.getPerson(id);

        if (person == null)
            return Response.status(NOT_FOUND).build();

        return Response.ok()
                       .entity(jsonConverter.getJSONFromPerson(person))
                       .build();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public Response post(String json)
    {
        Person person = jsonConverter.getPersonFromJson(json);
        person = persistence.addPerson(person);
        return Response.status(CREATED)
                       .entity(jsonConverter.getJSONFromPerson(person))
                       .build();
    }

    @PUT
    @Path("{id: [0-9]+}")
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public Response put(@PathParam("id") int id, String json)
    {
        Person person = jsonConverter.getPersonFromJson(json);
        person.setId(id);
        person = persistence.editPerson(person);

        if (person == null)
            return Response.status(NOT_FOUND).build();

        return Response.ok(jsonConverter.getJSONFromPerson(person)).build();
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
