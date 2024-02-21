package org.acme.dao;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Joke;
import org.acme.model.Rating;

import java.util.List;


@ApplicationScoped
public class RatingRepository implements PanacheRepository<Rating> {

    public double getAvgRatingForJob(Joke joke){
        System.out.println("jokeId " + joke.getId());
        List<Rating> ratings = list("joke", joke);
        System.out.println("ratings" + ratings);
        Double rating = Panache.getEntityManager().createQuery("SELECT avg(r.rating) FROM Rating r where r.joke = :joke", Double.class)
                .setParameter("joke", joke)
                .getSingleResult();
        return rating;
    }
}