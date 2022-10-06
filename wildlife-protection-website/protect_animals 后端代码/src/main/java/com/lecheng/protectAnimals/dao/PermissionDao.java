package com.lecheng.protectAnimals.dao;

import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public interface PermissionDao {
    Set<String> findPermissionListByUserId(int userId) throws Exception;
}
