package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dao.CategoryRepository;
import org.acme.dao.JokeRepository;
import org.acme.dto.JokeDTO;
import org.acme.model.Category;
import org.acme.model.Joke;

import java.util.List;
import java.util.Optional;

@Path("/joke")
public class JokeResource {

    @Inject
    JokeRepository jokeRepository;
    @Inject
    CategoryRepository categoryRepository;

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
    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(JokeDTO jokeDTO){
        if (validInput(jokeDTO)){
            Joke joke = new Joke();
            joke.setText(jokeDTO.getText());
            joke.setAuthor(jokeDTO.getAuthor());
            Optional<Category> categoryOptional = categoryRepository.listByName(jokeDTO.getCategory());
            if (categoryOptional.isPresent()){
                joke.setCategory(categoryOptional.get());
            }
            else {
                Category category = new Category();
                category.setName(jokeDTO.getCategory());
                category.getJokeList().add(joke);
                categoryRepository.persist(category);
                joke.setCategory(category);
            }
            jokeRepository.persist(joke);
            return Response.ok().entity("OK").build();
        }
        return Response.status(400).entity("Invalid inputs").build();
    }

    private boolean validInput(JokeDTO jokeDTO) {
        return !(jokeDTO.getText().isEmpty() || jokeDTO.getAuthor().isEmpty() || jokeDTO.getCategory().isEmpty());
    }


}
