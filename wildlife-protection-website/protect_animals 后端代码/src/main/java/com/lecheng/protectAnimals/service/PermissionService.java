package com.lecheng.protectAnimals.service;

import java.util.Set;

public interface PermissionService {
    Set<String> findPermissionListByUserId(int userId) throws Exception;
}
