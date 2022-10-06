package com.lecheng.protectAnimals.service;

import java.util.Set;

public interface RoleService {
    Set<String> findRoleListByUserId(int userId) throws Exception;
}
