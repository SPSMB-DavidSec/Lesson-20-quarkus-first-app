package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Person;

import java.util.List;
import java.util.Random;

@Path("/persons")
public class PersonResource {
    @Inject
    EntityManager em;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Person> persons = em.createQuery("select p from Person p", Person.class).getResultList();
        return Response.ok().entity(persons).build();
    }
}
