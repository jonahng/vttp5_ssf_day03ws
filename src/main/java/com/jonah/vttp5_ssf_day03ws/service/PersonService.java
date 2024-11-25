package com.jonah.vttp5_ssf_day03ws.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Person readPersonFile(String personID){
        String filePath = "C:\\Users\\65932\\vttp5_ssf_day03ws\\FILES\\" + personID + ".txt";
        List<String> fileData = new ArrayList<>();
        Person personFromFile = new Person();
        if(new File(filePath).isFile()){
            System.out.println("File found!: " + personID);
            try {FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineRead = "";
            
            
                while(((lineRead = bufferedReader.readLine())!=null)){
                    fileData.add(lineRead);
                    System.out.println("data read from file: " + lineRead);
                }
                bufferedReader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));

            Date dateRead = null;
            try {
                dateRead = sdf.parse(fileData.get(3));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            personFromFile = new Person(fileData.get(0),fileData.get(1),
            Long.parseLong(fileData.get(2)), dateRead
             );

            System.out.println("read file! returning person object");
            return personFromFile;

        }
        else{
            System.out.println("file not found!");
            return personFromFile;
        }

    }

    public List<String> availableFiles(){
        List<String> existingFiles = Stream.of(new File("C:\\Users\\65932\\vttp5_ssf_day03ws\\FILES").listFiles())
        .filter(file -> !file.isDirectory())
        .map(File::getName)
        .collect(Collectors.toList());

        List<String> filesWithoutExtension = new ArrayList<>();
        for(String fileName : existingFiles){
            
            filesWithoutExtension.add(fileName.substring(0,fileName.lastIndexOf('.')));
        }
        return filesWithoutExtension;
    }



}
