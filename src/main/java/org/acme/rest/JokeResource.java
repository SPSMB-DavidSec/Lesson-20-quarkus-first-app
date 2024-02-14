package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dao.JokeRepository;
import org.acme.model.Joke;

import java.util.List;

@Path("/joke")
public class JokeResource {

    @Inject
    JokeRepository jokeRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Joke> jokes = jokeRepository.listAll();
        return Response.ok().entity(jokes).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Joke joke = jokeRepository.findById(id);
        return Response.ok().entity(joke).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        jokeRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }


}
