package com.lecheng.protectAnimals.service;


import com.lecheng.protectAnimals.pojo.Animal;
import com.lecheng.protectAnimals.pojo.FAKInterface;

import java.util.List;

public interface AnimalService {
    int addAnimal(Animal animal);
    List<Animal> getAnimals(String recommends);
    List<FAKInterface> getFAKInterface();
    Animal getAnimal(String name);
    List<Animal> getAnimalsByType(String type);
}
