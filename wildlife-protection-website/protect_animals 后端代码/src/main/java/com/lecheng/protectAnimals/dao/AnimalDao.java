package com.lecheng.protectAnimals.dao;


import com.lecheng.protectAnimals.pojo.Animal;
import com.lecheng.protectAnimals.pojo.FAKInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AnimalDao {
    int addAnimal(Animal animal);
    List<Animal> getAnimals(List<String> list);
    List<FAKInterface> getFAKInterface();
    Animal getAnimal(String name);
    List<Animal> getAnimalsByTags(String tags);

}
