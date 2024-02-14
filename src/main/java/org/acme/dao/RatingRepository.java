package org.acme.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Person;
import org.acme.model.Rating;

import java.util.List;


@ApplicationScoped
public class RatingRepository implements PanacheRepository<Rating> {

}