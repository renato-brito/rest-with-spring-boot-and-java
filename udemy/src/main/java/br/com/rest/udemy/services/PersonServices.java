package br.com.rest.udemy.services;

import br.com.rest.udemy.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        logger.info("findById: " + id);

        // mock
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("123 Main St");
        person.setGender("M");

        return person;
    }

    public List<Person> findAll() {
        logger.info("finding all people !!");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
         return persons;
    }

    public Person create(Person person) {
        logger.info("Creating one person !");
        return person;
    }

    public Person update(Person person) {
        logger.info("Update one person !");
        return person;
    }

    public void delete(String id) {
        logger.info("Delete one person !");
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("John" + i);
        person.setLastName("Doe" + i);
        person.setAddress("123 Main St" + i);
        person.setGender("M");
        return person;
    }
}
