package com.lecheng.protectAnimals.service.impl;

import com.lecheng.protectAnimals.dao.RoleDao;
import com.lecheng.protectAnimals.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    //日志打印
    private static Logger logger = Logger.getLogger(RoleServiceImpl.class);
    @Override
    public Set<String> findRoleListByUserId(int userid) throws Exception {
        try {
            return roleDao.findRoleListByUserId(userid);
        } catch (Exception e) {
            logger.error("获取失败");
            return  null;
        }
    }
}
