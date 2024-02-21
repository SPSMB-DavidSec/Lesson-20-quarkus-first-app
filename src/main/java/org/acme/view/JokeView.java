package org.acme.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.acme.dao.JokeRepository;
import org.acme.dao.RatingRepository;
import org.acme.model.Joke;

import java.util.List;

@Named
@RequestScoped
public class JokeView {
    @Inject
    JokeRepository jokeRepository;
    @Inject
    RatingRepository ratingRepository;

    List<Joke> jokes;

    @PostConstruct
    public void init(){
        jokes = jokeRepository.listAll();
    }

    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public double getAvgRating(Joke joke){

        return ratingRepository.getAvgRatingForJob(joke);
    }
}
