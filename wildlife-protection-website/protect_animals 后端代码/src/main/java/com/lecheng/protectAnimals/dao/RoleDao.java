package com.lecheng.protectAnimals.dao;

import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public interface RoleDao {
    Set<String> findRoleListByUserId(int userId) throws Exception;
}
