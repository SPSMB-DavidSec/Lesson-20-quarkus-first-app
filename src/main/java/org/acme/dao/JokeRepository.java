package org.acme.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Joke;

@ApplicationScoped
public class JokeRepository implements PanacheRepository<Joke> {

}
