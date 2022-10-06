package com.lecheng.protectAnimals.controller;


import com.lecheng.protectAnimals.pojo.Animal;
import com.lecheng.protectAnimals.pojo.FAKInterface;
import com.lecheng.protectAnimals.pojo.ResponseMessage;
import com.lecheng.protectAnimals.pojo.Search;
import com.lecheng.protectAnimals.service.AnimalService;
import com.lecheng.protectAnimals.service.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @Autowired
    private SearchService searchService;
    /**
     * 日志打印
     */
    private static Logger log = Logger.getLogger(DynamicsController.class);

    /**
     * 获取推荐动物
     *
     * @param recommends
     * @return
     */
    @RequestMapping("/getAnimal")
    public ResponseMessage getAnimal(String recommends) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        List<Animal> animals = null;
        try {
            animals = animalService.getAnimals(recommends);
            return responseMessage.Success(animals);
        } catch (Exception e) {
            log.info("getAnimal异常");
            return responseMessage.False();
        }
    }

    /**
     * 获取急救知识发布接口
     */
    @RequestMapping("/getFAKInterface")
    public ResponseMessage getFAKInterface() {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        List<FAKInterface> fak = null;
        try {
            fak = animalService.getFAKInterface();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("getFAKInterface异常");
            return responseMessage.False();
        }
        return responseMessage.Success(fak);
    }

    /**
     * 搜索动物
     *
     * @param search
     * @return
     */
    @RequestMapping("/searchAnimal")
    public ResponseMessage searchAnimal(Search search) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        Animal animal = null;
        try {
            animal = animalService.getAnimal(search.getSearchcontext());
            searchService.addSearch(search);
            return responseMessage.Success(animal);
        } catch (Exception e) {
            log.info(e.getMessage());
            return responseMessage.False();
        }
    }

    /**
     * 获取最近搜索和热门搜索
     *
     * @return
     */
    @RequestMapping("/getCurrentSearchesAndHotSearches")
    public ResponseMessage getCurrentSearchesAndHotSearches(Integer userid) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<String> currentSearches = searchService.getCurrentSearches(userid);
            List<String> hotSearches = searchService.getHotSearches();
            HashMap<Object, Object> map = new HashMap<>();
            map.put("CurrentSearches", currentSearches);
            map.put("HotSearches", hotSearches);
            return responseMessage.Success(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("getCurrentSearchesAndHotSearches异常");
            return responseMessage.False("获取最近搜索和热门搜索");
        }

    }

    /**
     * 获取一级或二级动物
     *
     * @param type
     * @return
     */
    @RequestMapping("/getAnimalsByType")
    public ResponseMessage getAnimalsByType(String type) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<Animal> animals = animalService.getAnimalsByType(type);
            return responseMessage.Success(animals);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getAnimalsByType错误");
            return responseMessage.False("获取一级或二级动物失败");
        }
    }
}
