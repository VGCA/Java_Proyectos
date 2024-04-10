package com.batch.persistence;

import org.springframework.data.repository.CrudRepository;

import com.batch.entity.Person;

public interface PersonDAO extends CrudRepository<Person,Long>{
    
}
