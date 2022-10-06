package com.lecheng.protectAnimals.service.impl;

import com.lecheng.protectAnimals.dao.PermissionDao;
import com.lecheng.protectAnimals.service.PermissionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    //日志打印
    private static Logger logger = Logger.getLogger(PermissionServiceImpl.class);
    @Override
    public Set<String> findPermissionListByUserId(int userid) throws Exception {
        try {
            return permissionDao.findPermissionListByUserId(userid);
        } catch (Exception e) {
            logger.error("获取失败");
            return  null;
        }
    }
}
