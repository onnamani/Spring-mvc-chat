package com.springmvc.homepage.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalListService {
    private List<String> animals;

    @PostConstruct
    public void postConstruct() {
        this.animals = new ArrayList<>();
    }

    public void addAnimals(String animal) {
        animals.add(animal);
    }

    public List<String> getAnimals() {
        return animals;
    }
}
