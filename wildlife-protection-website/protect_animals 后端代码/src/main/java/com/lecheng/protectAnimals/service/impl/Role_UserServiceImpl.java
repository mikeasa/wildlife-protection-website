package com.lecheng.protectAnimals.service.impl;


import com.lecheng.protectAnimals.dao.Role_UserDao;
import com.lecheng.protectAnimals.pojo.Role_User;
import com.lecheng.protectAnimals.service.Role_UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Role_UserServiceImpl implements Role_UserService {
    @Autowired
    private Role_UserDao role_userDao;
    //日志打印
    private static Logger logger = Logger.getLogger(Role_UserServiceImpl.class);
    @Override
    public int addRole_User(Role_User role_user) {
        try {
            return role_userDao.addRole_User(role_user);
        } catch (Exception e) {
            logger.error("添加失败");
            return  -1;
        }
    }
}
