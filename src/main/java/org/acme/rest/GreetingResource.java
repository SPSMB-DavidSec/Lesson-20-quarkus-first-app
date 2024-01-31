package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dao.PersonRepository;
import org.acme.model.Person;

import java.util.List;
import java.util.Random;

@Path("/hello")
public class GreetingResource {
    @Inject
    PersonRepository personRepository;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String hello() {

        Person p = new Person();
        p.setName("Some test person");
        p.setAge(new Random().nextInt(100));
        List<Person> persons = personRepository.listAll();
        return "Hello from RESTEasy Reactive" + persons;
    }
}
