package br.com.rest.udemy.services;

import br.com.rest.udemy.exceptions.ResourceNotFoundException;
import br.com.rest.udemy.model.Person;
import br.com.rest.udemy.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());
    @Autowired
    private PersonRepository repository;

    public Person findById(Long id) {
        logger.info("findById: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID: " + id));
    }

    public List<Person> findAll() {
        logger.info("finding all people !!");
        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating one person !");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Update one person !");

        Person personToUpdate = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID: " + person.getId()));

        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setGender(person.getGender());

        return repository.save(personToUpdate);
    }

    public void delete(Long id) {
        logger.info("Delete one person !");

        Person personToDelete = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID: " + id));
        repository.delete(personToDelete);
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
