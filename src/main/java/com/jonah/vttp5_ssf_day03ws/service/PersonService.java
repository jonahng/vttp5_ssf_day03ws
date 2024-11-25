package com.jonah.vttp5_ssf_day03ws.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonah.vttp5_ssf_day03ws.Vttp5SsfDay03wsApplication;
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


    //working code that creates a random 8 digit id;
    public String generateID(){
        Random random = new Random();
        int randomInt = random.nextInt();
        System.out.println("id generated is:" +  String.format("%02x", randomInt));
        return String.format("%02x", randomInt);
    }


    public void createFile(String fileName){
        
        File file = new File("C:\\Users\\65932\\vttp5_ssf_day03ws\\FILES\\" + fileName + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("created file " + fileName + " in dir: " +   "FILES");
    }


    //maybe should not take in file, should take in filename?
    public void writeToFile(String personID, Person person) throws IOException{
        FileWriter myWriter = new FileWriter("C:\\Users\\65932\\vttp5_ssf_day03ws\\FILES\\" + personID + ".txt");
        myWriter.write(person.getFirstName() +"\n");
        myWriter.write(person.getEmail() +"\n");
        myWriter.write(person.getPhoneNumber() +"\n");
        myWriter.write(person.getDob() +"\n");

        myWriter.close();
        System.out.println("tried writing to the new file!");

    }



}
