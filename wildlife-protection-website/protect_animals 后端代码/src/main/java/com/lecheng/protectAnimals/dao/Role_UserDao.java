package com.lecheng.protectAnimals.dao;


import com.lecheng.protectAnimals.pojo.Role_User;
import org.springframework.stereotype.Component;

@Component
public interface Role_UserDao {
    int addRole_User(Role_User role_user);
}
