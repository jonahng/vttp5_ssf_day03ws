package com.jonah.vttp5_ssf_day03ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonah.vttp5_ssf_day03ws.model.Person;
import com.jonah.vttp5_ssf_day03ws.repo.PersonRepo;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;

    public List<Person> findAll(){
        return personRepo.findAll();
    }

    public Boolean create(Person person){
        return personRepo.create(person);
    }



}
