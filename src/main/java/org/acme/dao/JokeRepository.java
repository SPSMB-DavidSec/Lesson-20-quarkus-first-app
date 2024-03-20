package org.acme.dao;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Category;
import org.acme.model.Joke;

import java.util.List;

@ApplicationScoped
public class JokeRepository implements PanacheRepository<Joke> {

    public List<Joke> findByCategoryName(Category category){
        return find("category", category).list();
    }

    public List<Joke> findByCategoryName(String categoryName){
        return Panache.getEntityManager()
                .createQuery("SELECT j FROM Joke j JOIN FETCH j.category c WHERE c.name = :categoryName", Joke.class)
                .setParameter("categoryName", categoryName)
                .getResultList();

    }

}
