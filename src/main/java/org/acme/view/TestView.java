package org.acme.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import org.acme.model.Person;

import java.util.List;

@Named
@RequestScoped
public class TestView {

    @Inject
    EntityManager em;

    private int number;
    List<Person> persons;

    @PostConstruct
    public void init(){
        number = 10;
        persons = em.createQuery("select p from Person p", Person.class).getResultList();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
