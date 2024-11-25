package com.jonah.vttp5_ssf_day03ws.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jonah.vttp5_ssf_day03ws.model.Person;
import com.jonah.vttp5_ssf_day03ws.service.PersonService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService personService;


    @GetMapping("")
    public String personsList(Model model) {
        List<Person> personList = personService.findAll();
        model.addAttribute("persons",personList);
        return "personList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        System.out.println("mapping to /create works.");
        Person p = new Person();
        model.addAttribute("person", p);
        personService.generateID();
        return "personcreate";
    }

    @PostMapping("/create")
    public String postCreateForm(@Valid @ModelAttribute ("person") Person person, BindingResult result, Model model){
        if(result.hasErrors()){
            return "personcreate";
        }

        //creating a new person
        Person p = new Person(person.getFirstName(), person.getEmail(), person.getPhoneNumber(), person.getDob());
        personService.create(p);

        //generating unique id, creating txt file, writing to txt file
        String personID = personService.generateID();
        personService.createFile(personID);
        try {
            personService.writeToFile(personID, p);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:/persons";
    }

    @GetMapping("/{person-id}")
    public String accessPerson(@PathVariable("person-id") String personId, Model model) {
        //create function to get person from txt file
        //Person p = personService.(getPersonFromTXTFILE)
        //model.addAttribute("person", p);
        return "personpage";
    }
    
    


    
}
