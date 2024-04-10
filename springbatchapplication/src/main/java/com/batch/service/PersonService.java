package com.batch.service;

import java.util.List;

import com.batch.entity.Person;

public interface PersonService {
    void saveAll(List<Person> personList);
    void save(Person person);
}
