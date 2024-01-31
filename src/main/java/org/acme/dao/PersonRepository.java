package org.acme.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Person;


@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

}
