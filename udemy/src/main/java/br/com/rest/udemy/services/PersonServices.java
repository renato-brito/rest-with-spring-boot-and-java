package br.com.rest.udemy.services;

import br.com.rest.udemy.data.vo.v1.PersonVO;
import br.com.rest.udemy.exceptions.ResourceNotFoundException;
import br.com.rest.udemy.mapper.DozerMapper;
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

    public PersonVO findById(Long id) {
        logger.info("findById: " + id);

        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID: " + id));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("finding all people !!");
        return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one person !");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Update one person !");

        var personToUpdate = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID: " + person.getId()));

        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(personToUpdate), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        logger.info("Delete one person !");

        var personToDelete = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID: " + id));
        repository.delete(personToDelete);
    }

    private PersonVO mockPersonVO(int i) {
        PersonVO person = new PersonVO();
        person.setId(counter.incrementAndGet());
        person.setFirstName("John" + i);
        person.setLastName("Doe" + i);
        person.setAddress("123 Main St" + i);
        person.setGender("M");
        return person;
    }
}
