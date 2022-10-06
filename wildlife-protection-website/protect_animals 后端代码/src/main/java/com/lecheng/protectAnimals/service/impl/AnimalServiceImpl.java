package com.lecheng.protectAnimals.service.impl;

import com.lecheng.protectAnimals.dao.AnimalDao;
import com.lecheng.protectAnimals.dao.SearchDao;
import com.lecheng.protectAnimals.pojo.Animal;
import com.lecheng.protectAnimals.pojo.FAKInterface;
import com.lecheng.protectAnimals.service.AnimalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalDao animalDao;
    private SearchDao searchDao;
    //日志打印
    private static Logger logger = Logger.getLogger(AnimalServiceImpl.class);
    @Override
    public int addAnimal(Animal animal) {
        try {
            return animalDao.addAnimal(animal);
        } catch (Exception e) {
            logger.error("添加失败");
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Animal> getAnimals(String recommends) {

            List<String> split=new ArrayList<>();
            String[] temp=null;
            if (recommends!=null){
                temp = recommends.split(",");
                split=Arrays.asList(temp);
            }else {
                split.add("哺乳");
                split.add("鸟");
            }
        List<Animal> animals = null;
        try {
            animals = animalDao.getAnimals(split);
            return animals;
        } catch (Exception e) {
            logger.error("getAnimals失败");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<FAKInterface> getFAKInterface() {
        try {
            return animalDao.getFAKInterface();
        } catch (Exception e) {
            logger.error("getFAK失败");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Animal getAnimal(String name) {
        try {
            return animalDao.getAnimal(name);
        } catch (Exception e) {
           throw new RuntimeException("getAnimal(String name)  FALSE");
        }
    }

    @Override
    public List<Animal> getAnimalsByType(String type) {
        try {
            List<Animal> animals = animalDao.getAnimalsByTags(type);
            return animals;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("getAnimalsByType失败");
            return null;
        }

    }
}
