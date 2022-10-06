package com.lecheng.protectAnimals.service.impl;

import com.lecheng.protectAnimals.dao.AdminReleaseDao;
import com.lecheng.protectAnimals.pojo.AdminReleaseEntrance;
import com.lecheng.protectAnimals.service.AdminReleaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminReleaseServiceImpl implements AdminReleaseService {
    @Autowired
    private AdminReleaseDao adminReleaseDao;
    //日志打印
    private static Logger logger = Logger.getLogger(AdminReleaseServiceImpl.class);

    @Override
    public List<AdminReleaseEntrance> getAdminReleaseEntrance() {
        try {
            return adminReleaseDao.getAdminReleaseEntrance();
        } catch (Exception e) {
            logger.error("管理员发布新闻获取失败");
          throw new RuntimeException("管理员发布新闻获取失败");
        }
    }

    @Override
    public AdminReleaseEntrance getAdminReleaseById(int id) {
        try {
            AdminReleaseEntrance adminReleaseById = adminReleaseDao.getAdminReleaseById(id);
            return adminReleaseById;
        } catch (Exception e) {
            logger.error("管理员发布新闻具体获取失败");
            throw new RuntimeException("管理员发布新闻具体获取失败");
        }
    }
}
