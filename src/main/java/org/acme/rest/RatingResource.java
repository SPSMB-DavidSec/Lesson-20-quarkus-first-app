package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dao.JokeRepository;
import org.acme.dao.RatingRepository;
import org.acme.dto.RatingDTO;
import org.acme.model.Joke;
import org.acme.model.Rating;

import java.util.Optional;

@Path("/rating")
public class RatingResource {
    @Inject
    RatingRepository ratingRepository;
    @Inject
    JokeRepository jokeRepository;

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response setRating(@PathParam("id") long jokeId, RatingDTO ratingDTO){
        if (!isValidInput(ratingDTO) || jokeId <= 0) {
            return Response.status(400).entity("Invalid rating or jokeId value").build();
        }
        Optional<Joke> jokeOptional = jokeRepository.findByIdOptional(jokeId);
        if (jokeOptional.isPresent()) {
            Joke joke = jokeOptional.get();
            Rating rating = new Rating();
            rating.setRating(ratingDTO.getRating());
            rating.setJoke(joke);
            joke.getRatingList().add(rating);
            ratingRepository.persist(rating);
            jokeRepository.persist(joke);
            return Response.ok().entity("OK").build();
        }
        return Response.status(404).entity(String.format("Joke with id %s not found.", jokeId)).build();
    }

    private boolean isValidInput(RatingDTO ratingDTO) {
        return (ratingDTO.getRating() > 0 && ratingDTO.getRating()<=5);
    }
}
