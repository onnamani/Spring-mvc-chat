package com.springmvc.homepage.controller;

import com.springmvc.homepage.POJO.AnimalForm;
import com.springmvc.homepage.service.AnimalListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnimalController {
    AnimalListService animalList;

    public AnimalController(AnimalListService animalList) {
        this.animalList = animalList;
    }

    @GetMapping("/animal")
    public String getAnimal(@ModelAttribute("animalObject") AnimalForm animalForm, Model model) {
        model.addAttribute("animals", this.animalList.getAnimals());
        return "animal";
    }

    @PostMapping("/animal")
    public String postAnimal(@ModelAttribute("animalObject") AnimalForm animalForm, Model model) {
        animalList.addAnimals(animalForm.getAnimalName());
        model.addAttribute("animals", animalList.getAnimals());
        animalForm.setAdjective("");
        animalForm.setAnimalName("");
        return "animal";
    }
}
