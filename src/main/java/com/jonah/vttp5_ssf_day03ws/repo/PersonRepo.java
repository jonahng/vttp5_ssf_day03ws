package com.jonah.vttp5_ssf_day03ws.repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jonah.vttp5_ssf_day03ws.model.Person;

@Repository
public class PersonRepo {
    
    private List<Person> personList = new ArrayList<>();

    public PersonRepo() throws ParseException{

        String birthDate = "1990-12-09";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse(birthDate);

        personList.add(new Person("testPerson1", "testPerson1@Email.sg", 91245024, birthday));
    }

    public Boolean create(Person person){
        personList.add(person);
        return true;
    }

    public List<Person> findAll(){
        return personList;
    }
    
}
