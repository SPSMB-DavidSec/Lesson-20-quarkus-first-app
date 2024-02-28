package org.acme.dao;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Joke;
import org.acme.model.Rating;

@ApplicationScoped
public class RatingRepository implements PanacheRepository<Rating> {

    public double getAvgRatingForJoke(Joke joke){
        Double rating = Panache.getEntityManager()
                .createQuery("SELECT avg(r.rating) FROM Rating r where r.joke = :joke", Double.class)
                .setParameter("joke", joke)
                .getSingleResult();

        return rating == null?0d:rating;
    }
}