package com.jonah.vttp5_ssf_day03ws.controller;

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
        return "personcreate";
    }

    @PostMapping("/create")
    public String postCreateForm(@Valid @ModelAttribute ("person") Person person, BindingResult result, Model model){
        if(result.hasErrors()){
            return "personcreate";
        }
        Person p = new Person(person.getFirstName(), person.getEmail(), person.getPhoneNumber(), person.getDob());
        personService.create(p);
        return "redirect:/persons";
    }
    


    
}
