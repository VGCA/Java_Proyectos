package com.batch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch.entity.Person;
import com.batch.persistence.PersonDAO;

@Service
public class PersonServiceIMP implements PersonService{

    @Autowired
    private PersonDAO personDAO;

    @Override
    public void save(Person person) {
        personDAO.save(person);
    }

    @Override
    public void saveAll(List<Person> personList) {
        personDAO.saveAll(personList);
    }
    
}
