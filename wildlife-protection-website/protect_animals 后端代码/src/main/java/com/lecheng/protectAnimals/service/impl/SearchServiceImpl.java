package com.lecheng.protectAnimals.service.impl;

import com.lecheng.protectAnimals.dao.RoleDao;
import com.lecheng.protectAnimals.dao.SearchDao;
import com.lecheng.protectAnimals.pojo.Search;
import com.lecheng.protectAnimals.service.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    //日志打印
    private static Logger logger = Logger.getLogger(SearchServiceImpl.class);

    @Override
    public List<String> getCurrentSearches(Integer userid) {
        try {
            return searchDao.getCurrentSearches(userid);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("getCurrentSearches异常");
            return null;
        }
    }

    @Override
    public List<String> getHotSearches() {
        try {
            return searchDao.getHotSearches();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("getHotSearches异常");
            return null;
        }
    }

    @Override
    public int addSearch(Search search) {
        try {
            return searchDao.addSearch(search);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("getHotSearches异常");
            return -1;
        }
    }
}
